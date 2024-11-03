package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import org.springframework.web.multipart.MultipartFile;

public interface AsyncImageEvaluationService {
    void processImageEvaluationAsync(ImageEvaluation imageEvaluation, MultipartFile imageFile);
}