package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;

public interface AsyncImageEvaluationService {
    void processImageEvaluationAsync(ImageEvaluation imageEvaluation, String imageFileUrl);
}