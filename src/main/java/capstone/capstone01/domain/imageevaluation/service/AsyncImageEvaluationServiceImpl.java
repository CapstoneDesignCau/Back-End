package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import capstone.capstone01.domain.ai.service.AIModelService;
import capstone.capstone01.domain.feedback.domain.Feedback;
import capstone.capstone01.domain.feedback.domain.repository.FeedbackRepository;
import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.imageevaluation.domain.repository.ImageEvaluationRepository;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.domain.learningmaterial.domain.repository.LearningMaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncImageEvaluationServiceImpl implements AsyncImageEvaluationService {

    private final AIModelService aiModelService;
    private final FeedbackRepository feedbackRepository;
    private final ImageEvaluationRepository imageEvaluationRepository;
    private final LearningMaterialRepository learningMaterialRepository;

    @Async
    @Override
    public void processImageEvaluationAsync(ImageEvaluation imageEvaluation, String imageFileUrl) {
        log.info("Processing image evaluation start");

        // 인공지능 서버에 이미지 전송 및 평가 결과 수신
        EvaluateResultResponseDto evaluationResult = aiModelService.evaluateImage(imageEvaluation.getId(), imageFileUrl);

        // 이미지 평가 Entity 에 점수 및 피드백 업데이트
        imageEvaluation.setScore(evaluationResult.getTotalScore());

        for (List<Object> feedbackData : evaluationResult.getFeedback()) {
            int feedbackId = (int) feedbackData.get(0);
            String feedbackMessage = (String) feedbackData.get(1);

            LearningMaterial learningMaterial = learningMaterialRepository.findById((long) feedbackId)
                    .orElse(null);

            Feedback feedback = Feedback.builder()
                    .content(feedbackMessage)
                    .imageEvaluation(imageEvaluation)
                    .learningMaterial(learningMaterial)
                    .build();
            feedbackRepository.save(feedback);
            imageEvaluation.addFeedback(feedback);
        }

        // 이미지 평가가 완료되었으므로 Finish 를 true 로 변경
        imageEvaluation.setIsFinish(true);
        imageEvaluationRepository.save(imageEvaluation);

        log.info("Processing image evaluation finish");
    }
}