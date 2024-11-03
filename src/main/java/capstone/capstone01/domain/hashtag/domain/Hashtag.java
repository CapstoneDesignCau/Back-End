package capstone.capstone01.domain.hashtag.domain;

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
public class Hashtag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtagId")
    private Long id;

    @Column(name = "hashtag", nullable = false)
    private String hashtag;

    @Setter
    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LearningHashtag> learningHashtags = new ArrayList<>();

}