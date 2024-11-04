package capstone.capstone01.domain.learningmaterial.domain.repository;


import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {

    @Query("SELECT lm FROM LearningMaterial lm WHERE lm.isDeleted = false ORDER BY lm.updatedAt DESC")
    List<LearningMaterial> findTopByIsDeletedFalseOrderByUpdatedAtDesc(Pageable pageable);
}