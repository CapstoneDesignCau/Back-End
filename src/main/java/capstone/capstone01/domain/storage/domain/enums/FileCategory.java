package capstone.capstone01.domain.storage.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileCategory {
    USER_PROFILE(
            "user/profile",
            20 * 1024 * 1024L, // 20MB
            1,
            List.of("jpg", "jpeg", "png", "gif", "bmp")
    ),
    POST(
            "post",
            20 * 1024 * 1024L, // 20MB
            5,
            List.of("jpg", "jpeg", "png", "gif", "bmp",
                    "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf", "hwp")
    ),
    ETC(
            "etc",
            20 * 1024 * 1024L, // 20MB
            5,
            List.of("jpg", "jpeg", "png", "gif", "bmp")
    );

    private final String directory; // S3 디렉토리에 저장되는 파일 구조
    private final Long maxFileSize; // 저장가능한 파일의 최대 크기 제한
    private final Integer maxFileCount; // 한번에 저장가능한 파일 개수 제한
    private final List<String> fileExtensionList; // 파일 확장자 제한
}