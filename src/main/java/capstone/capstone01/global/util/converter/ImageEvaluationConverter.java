package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationStatsDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationSummaryDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class ImageEvaluationConverter {

    public static ImageEvaluation toImageEvaluation(User user, FileSaveInfo fileSaveInfo) {
        return ImageEvaluation.builder()
                .score(0)
                .isFinish(false)
                .user(user)
                .evaluationImage(fileSaveInfo)
                .build();
    }

    public static ImageEvaluationResponseDto toImageEvaluationResponseDto(ImageEvaluation imageEvaluation) {
        return ImageEvaluationResponseDto.builder()
                .id(imageEvaluation.getId())
                .score(imageEvaluation.getScore())
                .isFinish(imageEvaluation.getIsFinish())
                .feedbacks(imageEvaluation.getFeedbacks().stream()
                        .map(FeedbackConverter::toFeedbackResponseDto)
                        .collect(Collectors.toList()))
                .evaluationImage(StorageConverter.toFileResponseDto(imageEvaluation.getEvaluationImage()))
                .moreInfo(imageEvaluation.getMoreInfo())
                .createdAt(imageEvaluation.getCreatedAt())
                .build();
    }

    public static ImageEvaluationSummaryDto toImageEvaluationSummaryDto(ImageEvaluation evaluation) {
        return ImageEvaluationSummaryDto.builder()
                .id(evaluation.getId())
                .evaluationImage(StorageConverter.toFileResponseDto(evaluation.getEvaluationImage()))
                .createdAt(evaluation.getCreatedAt())
                .isFinish(evaluation.getIsFinish())
                .build();
    }

    public static ImageEvaluationStatsDto toImageEvaluationStatsDto(List<Integer> recentScores, Double averageScore) {
        return ImageEvaluationStatsDto.builder()
                .recentScores(recentScores)
                .averageScore(averageScore)
                .build();
    }

}