package capstone.capstone01.domain.ai.service;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import capstone.capstone01.domain.feedback.domain.dto.request.FeedbackResponseDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChatGPTServiceMock implements ChatGPTService {
    // 동작 확인용 Mock Class

    @Override
    public List<FeedbackResponseDto> generateFeedback(EvaluateResultResponseDto evaluationResult) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //
        return List.of(
                FeedbackResponseDto.builder()
                        .content("Generated feedback from ChatGPT 1")
                        .materialId(1L)
                        .build(),
                FeedbackResponseDto.builder()
                        .content("Generated feedback from ChatGPT 2")
                        .materialId(2L)
                        .build(),
                FeedbackResponseDto.builder()
                        .content("Generated feedback from ChatGPT 3")
                        .materialId(3L)
                        .build()
        );
    }
}
