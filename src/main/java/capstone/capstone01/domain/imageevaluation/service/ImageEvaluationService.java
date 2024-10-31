package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageEvaluationService {
    List<Long> createImageEvaluations(String email ,List<MultipartFile> imageFiles);
}
