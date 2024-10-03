package capstone.capstone01.domain.storage.controller;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/storage")
@RestController
public class StorageController {

    private final StorageService storageService; // StorageService 주입

    @Operation(summary = "파일 업로드", description = "파일 업로드 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public CustomApiResponse<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        // 파일 업로드 로직
        FileCategory fileCategory = FileCategory.POST; // 파일 카테고리 설정
        FileSaveInfo fileSaveInfo = storageService.saveFile(file, fileCategory);

        // 파일 URL 반환
        return CustomApiResponse.of(SuccessStatus.FILE_UPLOAD_OK, fileSaveInfo.getFileUrl());
    }
}