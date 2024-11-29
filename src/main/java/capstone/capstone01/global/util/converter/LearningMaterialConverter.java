package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialResponseDto;
import capstone.capstone01.domain.learningmaterial.dto.response.LearningMaterialSummaryDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.dto.response.FileResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class LearningMaterialConverter {

    public static LearningMaterial toLearningMaterial(LearningMaterialCreateRequestDto requestDto, List<FileSaveInfo> savedImages) {
        return LearningMaterial.builder()
                .title(requestDto.getTitle())
                .referenceInfo(requestDto.getReferenceInfo())
                .tips(requestDto.getTips())
                .keyword(requestDto.getKeyword())
                .prettyManner(requestDto.getPrettyManner())
                .isDeleted(false)
                .images(savedImages)
                .build();
    }

    public static List<LearningHashtag> toLearningHashtags(LearningMaterial learningMaterial, List<Hashtag> hashtags) {
        return hashtags.stream()
                .map(hashtag -> LearningHashtag.builder()
                        .learningMaterial(learningMaterial)
                        .hashtag(hashtag)
                        .build())
                .collect(Collectors.toList());
    }

    public static LearningMaterialResponseDto toLearningMaterialResponseDto(LearningMaterial learningMaterial) {
        List<FileResponseDto> imageResponseDtos = learningMaterial.getImages().stream()
                .map(StorageConverter::toFileResponseDto)
                .collect(Collectors.toList());

        return LearningMaterialResponseDto.builder()
                .id(learningMaterial.getId())
                .title(learningMaterial.getTitle())
                .referenceInfo(learningMaterial.getReferenceInfo())
                .tips(learningMaterial.getTips())
                .prettyManner(learningMaterial.getPrettyManner())
                .keyWord(learningMaterial.getKeyword())
                .isDeleted(learningMaterial.getIsDeleted())
                .createdAt(learningMaterial.getCreatedAt())
                .images(imageResponseDtos)
                .build();
    }

    public static LearningMaterialSummaryDto toLearningMaterialSummaryDto(LearningMaterial learningMaterial) {
        FileResponseDto image = learningMaterial.getImages().isEmpty() ? null : StorageConverter.toFileResponseDto(learningMaterial.getImages().get(0));
        return LearningMaterialSummaryDto.builder()
                .id(learningMaterial.getId())
                .title(learningMaterial.getTitle())
                .image(image)
                .isDeleted(learningMaterial.getIsDeleted())
                .build();
    }

}