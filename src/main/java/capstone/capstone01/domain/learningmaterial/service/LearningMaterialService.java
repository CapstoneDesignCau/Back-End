package capstone.capstone01.domain.learningmaterial.service;

import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface LearningMaterialService {

   Long createLearningMaterial(LearningMaterialCreateRequestDto requestDto);

}
