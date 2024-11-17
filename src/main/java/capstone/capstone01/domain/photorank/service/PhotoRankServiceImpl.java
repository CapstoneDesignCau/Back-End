package capstone.capstone01.domain.photorank.service;

import capstone.capstone01.domain.photorank.domain.PhotoRank;
import capstone.capstone01.domain.photorank.domain.repository.PhotoRankRepository;
import capstone.capstone01.domain.photorank.dto.request.PhotoRankCreateRequestDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.global.util.converter.PhotoRankConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoRankServiceImpl implements PhotoRankService {

    private final PhotoRankRepository photoRankRepository;
    private final StorageService fileSaveInfoService;

    @Override

    public List<Long> createPhotoRank(PhotoRankCreateRequestDto photoRankCreateRequestDto, List<MultipartFile> files) {

        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Files are required");
        }

        List<FileSaveInfo> savedFiles = fileSaveInfoService.saveFileList(files, FileCategory.PHOTO_RANK);

        List<Long> photoRankIds = new ArrayList<>();
        for (FileSaveInfo fileSaveInfo : savedFiles) {
            PhotoRank photoRank = PhotoRankConverter.toPhotoRank(photoRankCreateRequestDto, fileSaveInfo);
            photoRankRepository.save(photoRank);
            photoRankIds.add(photoRank.getId());
        }

        return photoRankIds;
    }
}