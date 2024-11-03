package capstone.capstone01.domain.learningmaterial.domain;

import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
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
public class LearningMaterial extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learningMaterialId")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isDeleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(name = "referenceInfo", nullable = false)
    private String referenceInfo;

    @Column(name = "tips", nullable = false)
    private String tips;

    @Column(name = "prettyManner", nullable = false)
    private String prettyManner;

    @Setter
    @Builder.Default
    @OneToMany(mappedBy = "learningMaterial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LearningHashtag> learningHashtags = new ArrayList<>();

}