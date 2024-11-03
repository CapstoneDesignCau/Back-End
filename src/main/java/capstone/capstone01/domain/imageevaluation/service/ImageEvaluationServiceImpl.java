package capstone.capstone01.domain.imageevaluation.service;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.imageevaluation.domain.repository.ImageEvaluationRepository;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationResponseDto;
import capstone.capstone01.domain.imageevaluation.dto.response.ImageEvaluationSummaryDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.ImageEvaluationConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static capstone.capstone01.global.util.value.StaticValue.TOP_RECENT_EVALUATION_LIMIT;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ImageEvaluationServiceImpl implements ImageEvaluationService {

    private final AsyncImageEvaluationService asyncImageEvaluationService;
    private final ImageEvaluationRepository imageEvaluationRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    @Override
    public List<Long> createImageEvaluations(String email, List<MultipartFile> imageFiles) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        List<FileSaveInfo> savedFiles = storageService.saveFileList(imageFiles, FileCategory.EVALUATION);

        List<ImageEvaluation> imageEvaluations = savedFiles.stream()
                .map(file -> ImageEvaluationConverter.toImageEvaluation(user, file))
                .collect(Collectors.toList());

        imageEvaluationRepository.saveAll(imageEvaluations);

        //효율을 위해 비동기적으로 이미지 평가를 수행
        for (int i = 0; i < imageFiles.size(); i++) {
            asyncImageEvaluationService.processImageEvaluationAsync(imageEvaluations.get(i), imageFiles.get(i));
        }

        log.info("Image Evaluation Created Successfully");

        return imageEvaluations.stream()
                .map(ImageEvaluation::getId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageEvaluationSummaryDto> getRecentImageEvaluations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        List<ImageEvaluation> evaluations = imageEvaluationRepository.findByUserOrderByCreatedAtDesc(user, PageRequest.of(0, TOP_RECENT_EVALUATION_LIMIT));

        return evaluations.stream()
                .map(ImageEvaluationConverter::toImageEvaluationSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ImageEvaluationResponseDto getImageEvaluation(String email, Long id) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        ImageEvaluation evaluation = imageEvaluationRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new UserException(ErrorStatus.IMAGE_EVALUATION_NOT_FOUND));

        return ImageEvaluationConverter.toImageEvaluationResponseDto(evaluation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageEvaluationSummaryDto> getAllImageEvaluations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        List<ImageEvaluation> evaluations = imageEvaluationRepository.findByUser(user);

        return evaluations.stream()
                .map(ImageEvaluationConverter::toImageEvaluationSummaryDto)
                .collect(Collectors.toList());
    }
    
}