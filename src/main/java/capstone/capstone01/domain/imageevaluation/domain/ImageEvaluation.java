package capstone.capstone01.domain.imageevaluation.domain;

import capstone.capstone01.domain.feedback.domain.Feedback;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "isFinish", nullable = false)
    @Builder.Default
    private Boolean isFinish = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "imageEvaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluationImageId", nullable = false)
    private FileSaveInfo evaluationImage;

}