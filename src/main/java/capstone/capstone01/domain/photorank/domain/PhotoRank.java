package capstone.capstone01.domain.photorank.domain;


import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PhotoRank extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photoRankId")
    private Long id;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Setter
    @Column(name = "appearanceCount", nullable = false)
    private int appearanceCount;

    @Setter
    @Column(name = "selectedCount", nullable = false)
    private int selectedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileSaveInfoId", nullable = false)
    private FileSaveInfo fileSaveInfo;

}