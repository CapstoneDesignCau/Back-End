package capstone.capstone01.domain.storage.domain.repository;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileSaveInfoRepository extends JpaRepository<FileSaveInfo, Long> {

    Optional<FileSaveInfo> findByFileUrl(String fileUrl);
    Optional<FileSaveInfo> findByFileKey(String fileKey);

}
