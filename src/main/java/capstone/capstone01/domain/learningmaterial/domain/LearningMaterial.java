package capstone.capstone01.domain.learningmaterial.domain;

import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LearningMaterial extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learningMaterialId")
    private Long id;

    @OneToMany(mappedBy = "learningMaterial")
    private List<LearningHashtag> learningMaterialHashtags;

    // Todo: 다른 필드들 정의



}