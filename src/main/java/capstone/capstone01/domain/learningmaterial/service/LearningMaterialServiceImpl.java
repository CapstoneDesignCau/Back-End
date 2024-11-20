package capstone.capstone01.domain.learningmaterial.service;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.hashtag.domain.repository.HashtagRepository;
import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.domain.learningmaterial.domain.repository.LearningMaterialRepository;
import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialResponseDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialSummaryDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.LearningMaterialException;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.HashtagConverter;
import capstone.capstone01.global.util.converter.LearningMaterialConverter;
import capstone.capstone01.global.util.value.StaticValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class LearningMaterialServiceImpl implements LearningMaterialService {

    private final StorageService storageService;
    private final LearningMaterialRepository learningMaterialRepository;
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Long createLearningMaterial(LearningMaterialCreateRequestDto requestDto, List<MultipartFile> imageFiles) {


        List<FileSaveInfo> savedImages = imageFiles != null ? storageService.saveFileList(imageFiles, FileCategory.MATERIAL) : List.of();

        LearningMaterial learningMaterial = LearningMaterialConverter.toLearningMaterial(requestDto, savedImages);

        learningMaterialRepository.save(learningMaterial);

        return learningMaterial.getId();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLearningMaterial(Long id) {
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id)
                .orElseThrow(() ->new LearningMaterialException(ErrorStatus.LEARNING_MATERIAL_NOT_FOUND));
        learningMaterial.setIsDeleted(true);
        learningMaterialRepository.save(learningMaterial);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void restoreLearningMaterial(Long id) {
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id)
                .orElseThrow(() -> new LearningMaterialException(ErrorStatus.LEARNING_MATERIAL_NOT_FOUND));
        learningMaterial.setIsDeleted(false);
        learningMaterialRepository.save(learningMaterial);
    }

    @Override
    @Transactional(readOnly = true)
    public LearningMaterialResponseDto getLearningMaterial(String email, Long id) {
        LearningMaterial learningMaterial = learningMaterialRepository.findById(id)
                .orElseThrow(() -> new LearningMaterialException(ErrorStatus.LEARNING_MATERIAL_NOT_FOUND));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        // 삭제되기 전에 생성된 사진 피드백의 경우 삭제된 학습자료를 가리킬수 있으므로 삭제해도 접근은 가능하게 허용
//        if (!user.getRole().equals(UserRole.ADMIN) && learningMaterial.getIsDeleted()) {
//            throw new LearningMaterialException(ErrorStatus.LEARNING_MATERIAL_DELETED);
//        }

        return LearningMaterialConverter.toLearningMaterialResponseDto(learningMaterial);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearningMaterialSummaryDto> getLearningMaterials() {
        Pageable pageable = PageRequest.of(0, StaticValue.LEARNING_MATERIAL);
        List<LearningMaterial> materials = learningMaterialRepository.findTopByIsDeletedFalseOrderByUpdatedAtDesc(pageable);
        return materials.stream()
                .map(LearningMaterialConverter::toLearningMaterialSummaryDto)
                .collect(Collectors.toList());
    }

    private Hashtag getOrCreateHashtag(String tagName) {
        return hashtagRepository.findByHashtag(tagName)
                .orElseGet(() -> {
                    Hashtag newHashtag = HashtagConverter.toHashtag(tagName);
                    return hashtagRepository.save(newHashtag);
                });
    }

}