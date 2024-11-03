package capstone.capstone01.domain.learningmaterial.domain.repository;


import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {

}