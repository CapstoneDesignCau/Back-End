package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.domain.enums.FileInfo;

import java.util.Map;

public class StorageConverter {

    public static FileSaveInfo toFileSaveInfo(String uuid, Map<FileInfo, String> fileInfoStringMap, String originFileName, String extension, FileCategory fileCategory) {
        return FileSaveInfo.builder()
                .uuid(uuid)
                .fileKey(fileInfoStringMap.get(FileInfo.FILE_KEY))
                .fileUrl(fileInfoStringMap.get(FileInfo.FILE_URL))
                .originFileName(originFileName)
                .extension(extension)
                .fileCategory(fileCategory)
                .build();
    }
}