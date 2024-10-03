package capstone.capstone01.domain.storage.domain;

import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FileSaveInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuidId")
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "originFileName", nullable = false)
    private String originFileName;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "fileKey", unique = true, nullable = false)
    private String fileKey;

    @Column(name = "fileUrl", unique = true, nullable = false)
    private String fileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "fileCategory", nullable = false)
    private FileCategory fileCategory;

}