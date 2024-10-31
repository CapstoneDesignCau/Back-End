package capstone.capstone01.domain.learnighashtag.domain;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LearningHashtag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learningHashtagId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "learningMaterialId", nullable = false)
    private LearningMaterial learningMaterial;

    @ManyToOne
    @JoinColumn(name = "hashtagId", nullable = false)
    private Hashtag hashtag;

}