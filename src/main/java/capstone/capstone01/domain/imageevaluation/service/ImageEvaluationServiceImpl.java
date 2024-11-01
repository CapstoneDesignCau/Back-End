package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.imageevaluation.domain.repository.ImageEvaluationRepository;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.ImageEvaluationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageEvaluationServiceImpl implements ImageEvaluationService {

    private final StorageService storageService;
    private final ImageEvaluationRepository imageEvaluationRepository;
    private final UserRepository userRepository;

    @Override
    public List<Long> createImageEvaluations(String email, List<MultipartFile> imageFiles) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        List<FileSaveInfo> savedFiles = storageService.saveFileList(imageFiles, FileCategory.EVALUATION);

        List<ImageEvaluation> imageEvaluations = savedFiles.stream()
                .map(file -> ImageEvaluationConverter.toImageEvaluation(user, file))
                .collect(Collectors.toList());

        imageEvaluationRepository.saveAll(imageEvaluations);

        return imageEvaluations.stream()
                .map(ImageEvaluation::getId)
                .collect(Collectors.toList());
    }

}