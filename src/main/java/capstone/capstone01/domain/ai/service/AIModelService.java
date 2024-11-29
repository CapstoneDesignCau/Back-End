package capstone.capstone01.domain.ai.service;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;

public interface AIModelService {
    EvaluateResultResponseDto evaluateImage(Long imageEvaluationId,  String imageUrl);
}