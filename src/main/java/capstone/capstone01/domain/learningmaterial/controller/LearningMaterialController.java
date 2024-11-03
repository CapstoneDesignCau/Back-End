package capstone.capstone01.domain.learningmaterial.controller;

import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialResponseDto;
import capstone.capstone01.domain.learningmaterial.service.LearningMaterialService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/learning-material")
@RequiredArgsConstructor
public class LearningMaterialController {

    private final LearningMaterialService learningMaterialService;

    @Operation(summary = "학습 자료 생성", description = "학습 자료 생성 API (ADMIN 용)")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public CustomApiResponse<Long> createLearningMaterial(
            @Valid @RequestPart("learningMaterial") LearningMaterialCreateRequestDto requestDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> imageFiles
    ) {
        Long materialId = learningMaterialService.createLearningMaterial(requestDto, imageFiles);
        return CustomApiResponse.of(SuccessStatus.MATERIAL_CREATED, materialId);
    }

    @Operation(summary = "학습 자료 삭제", description = "학습 자료 삭제 API (ADMIN 용)")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{material-id}")
    public CustomApiResponse<Void> deleteLearningMaterial(@PathVariable("material-id") Long id) {
        learningMaterialService.deleteLearningMaterial(id);
        return CustomApiResponse.of(SuccessStatus.MATERIAL_OK, null);
    }

    @Operation(summary = "학습 자료 복구", description = "학습 자료 복구 API (ADMIN 용)")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/restore/{material-id}")
    public CustomApiResponse<Void> restoreLearningMaterial(@PathVariable("material-id") Long id) {
        learningMaterialService.restoreLearningMaterial(id);
        return CustomApiResponse.of(SuccessStatus.MATERIAL_OK, null);
    }

    @Operation(summary = "학습 자료 세부 정보 조회", description = "학습 자료 세부 정보 조회 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{material-id}")
    public CustomApiResponse<LearningMaterialResponseDto> getLearningMaterial(@PathVariable("material-id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        LearningMaterialResponseDto material = learningMaterialService.getLearningMaterial(email, id);
        return CustomApiResponse.of(SuccessStatus.MATERIAL_OK, material);
    }
}