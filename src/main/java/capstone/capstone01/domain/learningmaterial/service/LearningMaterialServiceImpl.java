package capstone.capstone01.domain.learningmaterial.service;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.hashtag.domain.repository.HashtagRepository;
import capstone.capstone01.domain.learnighashtag.domain.LearningHashtag;
import capstone.capstone01.domain.learningmaterial.domain.LearningMaterial;
import capstone.capstone01.domain.learningmaterial.domain.repository.LearningMaterialRepository;
import capstone.capstone01.domain.learningmaterial.dto.request.LearningMaterialCreateRequestDto;
import capstone.capstone01.global.util.converter.HashtagConverter;
import capstone.capstone01.global.util.converter.LearningMaterialConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LearningMaterialServiceImpl implements LearningMaterialService {

    private final LearningMaterialRepository learningMaterialRepository;
    private final HashtagRepository hashtagRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Long createLearningMaterial(LearningMaterialCreateRequestDto requestDto) {
        List<Hashtag> hashtags = requestDto.getHashtags().stream()
                .map(this::getOrCreateHashtag)
                .collect(Collectors.toList());

        LearningMaterial learningMaterial = LearningMaterialConverter.toLearningMaterial(requestDto);
        List<LearningHashtag> learningHashtags = LearningMaterialConverter.toLearningHashtags(learningMaterial, hashtags);

        learningMaterial.setLearningHashtags(learningHashtags);
        learningMaterialRepository.save(learningMaterial);

        return learningMaterial.getId();
    }

    private Hashtag getOrCreateHashtag(String tagName) {
        return hashtagRepository.findByHashtag(tagName)
                .orElseGet(() -> {
                    Hashtag newHashtag = HashtagConverter.toHashtag(tagName);
                    return hashtagRepository.save(newHashtag);
                });
    }

}