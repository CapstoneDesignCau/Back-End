package capstone.capstone01.domain.hashtag.service;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.hashtag.domain.repository.HashtagRepository;
import capstone.capstone01.domain.hashtag.dto.request.HashtagCreateRequestDto;
import capstone.capstone01.domain.hashtag.dto.response.HashtagResponseDto;
import capstone.capstone01.global.util.converter.HashtagConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;

    @Override
    public Long createHashtag(HashtagCreateRequestDto requestDto) {
        String name = requestDto.getName();
        if (name.startsWith("#")) {
            name = name.substring(1);
        }

        //동일한 이름의 해쉬태그가 이미 존재하는 지 확인
        Optional<Hashtag> existingHashtag = hashtagRepository.findByHashtag(name);
        if (existingHashtag.isPresent()) {
            return existingHashtag.get().getId();
        }

        Hashtag hashtag = HashtagConverter.toHashtag(name);
        hashtagRepository.save(hashtag);
        return hashtag.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HashtagResponseDto> getAllHashtags() {
        return hashtagRepository.findAll().stream()
                .map(HashtagConverter::toHashtagResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HashtagResponseDto> searchHashtags(String keyword) {
        return hashtagRepository.findByHashtagContaining(keyword).stream()
                .map(HashtagConverter::toHashtagResponseDto)
                .collect(Collectors.toList());
    }
}