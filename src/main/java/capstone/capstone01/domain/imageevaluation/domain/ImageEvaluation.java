package capstone.capstone01.domain.imageevaluation.domain;

import capstone.capstone01.domain.feedback.domain.Feedback;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImageEvaluation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageEvaluationId")
    private Long id;

    @Setter
    @Column(name = "score", nullable = false)
    private int score;

    @Setter
    @Column(name = "isFinish", nullable = false)
    @Builder.Default
    private Boolean isFinish = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "imageEvaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluationImageId", nullable = false)
    private FileSaveInfo evaluationImage;

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setImageEvaluation(this);
    }
}