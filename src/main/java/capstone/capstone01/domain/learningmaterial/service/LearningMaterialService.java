package capstone.capstone01.domain.learningmaterial.service;

import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialResponseDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LearningMaterialService {
   Long createLearningMaterial(LearningMaterialCreateRequestDto requestDto, List<MultipartFile> imageFiles);

   void deleteLearningMaterial(Long id);

   void restoreLearningMaterial(Long id);

   LearningMaterialResponseDto getLearningMaterial(String email, Long id);

   List<LearningMaterialSummaryDto> getLearningMaterials();

}
