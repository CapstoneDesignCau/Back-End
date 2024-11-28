package capstone.capstone01.domain.imageevaluation.controller;

import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationStatsDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationSummaryDto;
import capstone.capstone01.domain.imageevaluation.service.ImageEvaluationService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public CustomApiResponse<List<ImageEvaluationSummaryDto>> createImageEvaluations(
            @RequestParam("images") List<MultipartFile> imageFiles
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<ImageEvaluationSummaryDto> imageEvaluationSummaries = imageEvaluationService.createImageEvaluations(email, imageFiles);

        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_CREATED, imageEvaluationSummaries);
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

    @Operation(summary = "오늘 올린 이미지 평가 조회", description = "오늘 올린 평가 사진 최대 5개 조회 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/today")
    public CustomApiResponse<List<ImageEvaluationSummaryDto>> getTodayImageEvaluations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<ImageEvaluationSummaryDto> todayEvaluations = imageEvaluationService.getTodayImageEvaluations(email);
        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_OK, todayEvaluations);
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

    @Operation(summary = "내가 평가를 요청한 이미지 목록 조회", description = "내가 평가를 요청한 이미지 목록 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/list")
    public CustomApiResponse<Page<ImageEvaluationSummaryDto>> getUploadedImages(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Page<ImageEvaluationSummaryDto> images = imageEvaluationService.getUploadedImages(email, pageable);
        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_OK, images);
    }

    @Operation(summary = "로그인 한 유저의 최근 5개의 이미지 점수와 평균 점수 반환", description = "로그인 한 유저의 최근 5개의 이미지 점수와 평균 점수 반환 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/stats")
    public CustomApiResponse<ImageEvaluationStatsDto> getUserImageEvaluationStats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        ImageEvaluationStatsDto stats = imageEvaluationService.getUserImageEvaluationStats(email);
        return CustomApiResponse.of(SuccessStatus.IMAGE_EVALUATION_OK, stats);
    }

}