package capstone.capstone01.domain.imageevaluation.controller;

import capstone.capstone01.domain.imageevaluation.service.ImageEvaluationService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/imageEvaluation")
@RequiredArgsConstructor
public class ImageEvaluationController {

    private final ImageEvaluationService imageEvaluationService;

    @Operation(summary = "이미지 리스트들 평가 생성", description = "이미지 리스트들 평가 생성 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = "multipart/form-data")
    public CustomApiResponse<List<Long>> createImageEvaluations(
            @RequestParam("images") List<MultipartFile> imageFiles
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Long> imageEvaluationIds = imageEvaluationService.createImageEvaluations(email, imageFiles);

        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_CREATED, imageEvaluationIds);
    }

}