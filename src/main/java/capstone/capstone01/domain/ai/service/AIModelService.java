package capstone.capstone01.domain.ai.service;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface AIModelService {
    EvaluateResultResponseDto evaluateImage(Long imageEvaluationId, MultipartFile imageFile);
}