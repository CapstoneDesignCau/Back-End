package capstone.capstone01.domain.learningmaterial.domain;

import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class learningMaterial extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learnigMaterialId")
    private Long id;

    //Todo: 다른 필드들 정의


}