package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

public class LearningMaterialConverter {

    public static LearningMaterial toLearningMaterial(LearningMaterialCreateRequestDto requestDto) {
        return LearningMaterial.builder()
                .isDeleted(false)
                .title(requestDto.getTitle())
                .referenceInfo(requestDto.getReferenceInfo())
                .tips(requestDto.getTips())
                .prettyManner(requestDto.getPrettyManner())
                .build();
    }

    public static List<LearningHashtag> toLearningHashtags(LearningMaterial learningMaterial, List<Hashtag> hashtags) {
        return hashtags.stream()
                .map(hashtag -> LearningHashtag.createLearningHashtag(learningMaterial, hashtag))
                .collect(Collectors.toList());
    }
}