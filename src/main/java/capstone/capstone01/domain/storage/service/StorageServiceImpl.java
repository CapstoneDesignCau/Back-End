package capstone.capstone01.domain.storage.service;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.domain.repository.FileSaveInfoRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.FileException;
import capstone.capstone01.global.util.converter.StorageConverter;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final AmazonS3 amazonS3;
    private final FileSaveInfoRepository fileSaveInfoRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public FileSaveInfo saveFile(MultipartFile file, FileCategory fileCategory) {
        this.validateFile(file, fileCategory);

        String uuid = UUID.randomUUID().toString(); //동일 이름의 파일 구분을 위해 UUID 생성

        String filename = file.getOriginalFilename();
        String originFileName = this.getOriginFileName(filename); // 파일명만
        String extension = this.getExtension(filename); // 확장자만

        Map<FileInfo, String> fileInfoMap = this.uploadFileToS3(file, uuid, originFileName, extension, fileCategory);

        FileSaveInfo fileSaveInfo = StorageConverter.toFileSaveInfo(uuid,fileInfoMap,originFileName,extension,fileCategory);

        return fileSaveInfoRepository.save(fileSaveInfo);
    }

    @Override
    public List<FileSaveInfo> saveFileList(List<MultipartFile> fileList, FileCategory fileCategory) {
        this.validateFileListSize(fileList, fileCategory);
        return fileList.stream()
                .map(file -> this.saveFile(file, fileCategory))
                .toList();
    }

    @Override
    public FileSaveInfo updateFile(FileSaveInfo priorFileSaveInfo, @NotNull MultipartFile file, @NotNull FileCategory fileCategory) {
        if (priorFileSaveInfo != null) {
            this.deleteFile(priorFileSaveInfo);
        }
        return this.saveFile(file, fileCategory);
    }

    @Override
    public List<FileSaveInfo> updateFileList(List<FileSaveInfo> priorFileSaveInfoList, List<MultipartFile> fileList, FileCategory fileCategory) {
        if (!priorFileSaveInfoList.isEmpty()) {
            this.deleteFileList(priorFileSaveInfoList);
        }

        return this.saveFileList(fileList, fileCategory);
    }

    @Override
    public void deleteFile(@NotNull FileSaveInfo fileSaveInfo) {
        this.deleteFileToS3(fileSaveInfo.getFileKey());
        fileSaveInfoRepository.delete(fileSaveInfo);
    }

    @Override
    public void deleteFileList(@NotNull List<FileSaveInfo> fileSaveInfoList) {
        for (FileSaveInfo fileSaveInfo : fileSaveInfoList) {
            this.deleteFile(fileSaveInfo);
        }
    }

    @Override
    public void deleteFileByUrl(String fileUrl) {
        // 파일 키 추출
        String fileKey = extractFileKeyFromUrl(fileUrl);
        //System.out.println("Extracted fileKey: " + fileKey);

        Optional<FileSaveInfo> fileSaveInfoOptional = fileSaveInfoRepository.findByFileKey(fileKey);
        if (fileSaveInfoOptional.isEmpty()) {
            throw new FileException(ErrorStatus.FILE_WRONG_URL);
        } else {
            FileSaveInfo fileSaveInfo = fileSaveInfoOptional.get();
            this.deleteFile(fileSaveInfo);
            fileSaveInfoRepository.delete(fileSaveInfo);
        }
    }

    //S3에 파일 업로드
    private Map<FileInfo, String> uploadFileToS3(MultipartFile multipartFile, String uuid, String originFileName, String extension, FileCategory fileCategory) {

        String fileKey = this.makeFileKey(uuid, originFileName, extension, fileCategory);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize()); // 콘텐츠 길이 설정

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, fileKey, inputStream, objectMetadata));
        } catch (IOException e) {
            throw new FileException(ErrorStatus.FILE_UPLOAD_FAIL);
        }

        Map<FileInfo, String> fileInfoStringMap = new HashMap<>();
        fileInfoStringMap.put(FileInfo.FILE_URL, amazonS3.getUrl(bucketName, fileKey).toString().trim());
        fileInfoStringMap.put(FileInfo.FILE_KEY, fileKey);
        return fileInfoStringMap;
    }

    //S3에서 파일 삭제
    private void deleteFileToS3(String fileKey) {
        try {
            amazonS3.deleteObject(bucketName, fileKey);
        } catch (Exception e) {
            throw new FileException(ErrorStatus.FILE_DELETE_FAIL);
        }
    }

    //S3용 FileKey 만들기
    private String makeFileKey(String uuid, String originFileName, String extension, FileCategory fileCategory) {
        return fileCategory.getDirectory() + "/" + originFileName + "_" + uuid + "." + extension;
    }

    private String getOriginFileName(String originFileName) {
        return StringUtils.stripFilenameExtension(originFileName);
    }

    private String getExtension(String fileName) {
        return StringUtils.getFilenameExtension(fileName);
    }

    // 파일 크기, 확장자, Null 검증
    private void validateFile(MultipartFile file, FileCategory fileCategory) {
        if (file == null) {
            throw new FileException(ErrorStatus.FILE_NULL);
        }

        if (file.getSize() > fileCategory.getMaxFileSize()) {
            throw new FileException(ErrorStatus.FILE_SIZE_EXCEED);
        }

        //저장이 허용된 확장자인지 확인
        boolean isValidExtension = false;
        String extension = Objects.requireNonNull(this.getExtension(file.getOriginalFilename())).toLowerCase();

        for (String extensionType : fileCategory.getFileExtensionList()) {
            if (extension.equals(extensionType)) {
                isValidExtension = true;
                break;
            }
        }

        if (!isValidExtension) {
            throw new FileException(ErrorStatus.FILE_WRONG_EXTENSION);
        }
    }

    //파일 개수 검증
    private void validateFileListSize(List<MultipartFile> fileList, FileCategory fileCategory) {
        if (fileList.isEmpty()) {
            throw new FileException(ErrorStatus.FILE_NULL);
        }

        if (fileList.size() > fileCategory.getMaxFileCount()) {
            throw new FileException(ErrorStatus.FILE_COUNT_EXCEED);
        }
    }

    //Url 에서 FileKey 추출
    private String extractFileKeyFromUrl(String fileUrl) {
        //ex) https://bucket-name.s3.amazonaws.com/folder/file.ext -> folder/file.ext
        String delimiter = "amazonaws.com/";
        int index = fileUrl.indexOf(delimiter);
        if (index != -1) {
            return fileUrl.substring(index + delimiter.length());
        } else {
            throw new FileException(ErrorStatus.FILE_WRONG_URL);
        }
    }

}