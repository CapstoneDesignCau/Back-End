package capstone.capstone01.domain.learningmaterial.domain;

import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
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

    @Setter
    @Column(name = "isDeleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(name = "referenceInfo", nullable = false)
    private String referenceInfo;

    @Column(name = "tips", nullable = false)
    private String tips;

    @Column(name = "prettyManner", nullable = false)
    private String prettyManner;

    @Column(name="keyword", nullable = false)
    private String keyword;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "learningMaterialId")
    @Builder.Default
    private List<FileSaveInfo> images = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "learningMaterial", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LearningHashtag> learningHashtags = new ArrayList<>();

}