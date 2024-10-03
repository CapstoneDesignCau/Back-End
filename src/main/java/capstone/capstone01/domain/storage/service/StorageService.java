package capstone.capstone01.domain.storage.service;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    FileSaveInfo saveFile(MultipartFile file, FileCategory fileCategory);

    List<FileSaveInfo> saveFileList(List<MultipartFile> fileList, FileCategory fileCategory);

    FileSaveInfo updateFile(FileSaveInfo priorFileSaveInfo, @NotNull MultipartFile file, @NotNull FileCategory fileCategory);

    List<FileSaveInfo> updateFileList(List<FileSaveInfo> priorFileSaveInfoList, List<MultipartFile> fileList, FileCategory fileCategory);

    void deleteFile(@NotNull FileSaveInfo fileSaveInfo);

    void deleteFileList(@NotNull List<FileSaveInfo> fileSaveInfoList);

    void deleteFileByUrl(String fileUrl);
}