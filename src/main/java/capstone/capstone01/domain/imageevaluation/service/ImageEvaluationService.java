package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationSummaryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageEvaluationService {
    List<ImageEvaluationSummaryDto> createImageEvaluations(String email, List<MultipartFile> imageFiles);

    List<ImageEvaluationSummaryDto> getRecentImageEvaluations(String email);

    ImageEvaluationResponseDto getImageEvaluation(String email, Long id);

    List<ImageEvaluationSummaryDto> getAllImageEvaluations(String email);

    List<ImageEvaluationSummaryDto> getTodayImageEvaluations(String email);
}