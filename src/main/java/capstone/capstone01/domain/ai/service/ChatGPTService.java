package capstone.capstone01.domain.ai.service;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import capstone.capstone01.domain.feedback.domain.dto.request.FeedbackResponseDto;

import java.util.List;

public interface ChatGPTService {
    List<FeedbackResponseDto> generateFeedback(EvaluateResultResponseDto evaluationResult);
}
