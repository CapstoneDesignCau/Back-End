package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.feedback.domain.Feedback;
import capstone.capstone01.domain.feedback.domain.dto.request.FeedbackResponseDto;
import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;

public class FeedbackConverter {

    public static Feedback toFeedback(String content, ImageEvaluation imageEvaluation, LearningMaterial learningMaterial) {
        return Feedback.builder()
                .content(content)
                .learningMaterial(learningMaterial)
                .imageEvaluation(imageEvaluation)
                .build();
    }

    public static FeedbackResponseDto toFeedbackResponseDto(Feedback feedback) {
        Long materialId = null;
        if (feedback.getLearningMaterial() != null) {
            materialId = feedback.getLearningMaterial().getId();
        }

        return FeedbackResponseDto.builder()
                .feedbackId(feedback.getId())
                .content(feedback.getContent())
                .materialId(materialId)
                .build();
    }
    
}
