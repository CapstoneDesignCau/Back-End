package capstone.capstone01.domain.feedback.domain;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackId")
    private Long id;

    @Column(name = "content",length = 300, nullable = false)
    private String content;

    @ManyToOne
    @Setter
    @JoinColumn(name = "learningMaterialId", nullable = false)
    private LearningMaterial learningMaterial;

    @ManyToOne
    @Setter
    @JoinColumn(name = "imageEvaluationId", nullable = false)
    private ImageEvaluation imageEvaluation;

}