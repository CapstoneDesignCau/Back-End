package capstone.capstone01.domain.learningmaterial.controller;

import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
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

@RestController
@RequestMapping("/api/learning-material")
@RequiredArgsConstructor
public class LearningMaterialController {

    private final LearningMaterialService learningMaterialService;

    @Operation(summary = "학습 자료 생성", description = "학습 자료 생성 API (ADMIN 용)")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public CustomApiResponse<Long> createLearningMaterial(
            @Valid @RequestBody LearningMaterialCreateRequestDto requestDto
    ) {
        Long materialId = learningMaterialService.createLearningMaterial(requestDto);
        return CustomApiResponse.of(SuccessStatus.MATERIAL_CREATED, materialId);
    }

}