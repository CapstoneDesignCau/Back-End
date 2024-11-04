package capstone.capstone01.domain.imageevaluation.controller;

import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationSummaryDto;
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

    @Operation(summary = "특정 이미지 평가 조회", description = "특정 이미지 평가 조회 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{evaluation-id}")
    public CustomApiResponse<ImageEvaluationResponseDto> getImageEvaluation(@PathVariable("evaluation-id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        ImageEvaluationResponseDto evaluation = imageEvaluationService.getImageEvaluation(email, id);
        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_OK, evaluation);
    }

    @Operation(summary = "최근 5개의 이미지 평가 조회", description = "가장 최근에 생성된 본인의 5개의 이미지 평가 조회 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/recent")
    public CustomApiResponse<List<ImageEvaluationSummaryDto>> getRecentImageEvaluations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<ImageEvaluationSummaryDto> recentEvaluations = imageEvaluationService.getRecentImageEvaluations(email);
        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_OK, recentEvaluations);
    }

    @Operation(summary = "평가를 요청한 모든 이미지 목록 조회", description = "본인이 신청한 모든 이미지 평가 목록 조회 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/all")
    public CustomApiResponse<List<ImageEvaluationSummaryDto>> getAllImageEvaluations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<ImageEvaluationSummaryDto> evaluations = imageEvaluationService.getAllImageEvaluations(email);
        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_OK, evaluations);
    }

}