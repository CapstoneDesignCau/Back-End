package capstone.capstone01.domain.hashtag.service;

import capstone.capstone01.domain.hashtag.dto.request.HashtagCreateRequestDto;
import capstone.capstone01.domain.hashtag.dto.response.HashtagResponseDto;

import java.util.List;

public interface HashtagService {
    Long createHashtag(HashtagCreateRequestDto requestDto);

    List<HashtagResponseDto> getAllHashtags();

    List<HashtagResponseDto> searchHashtags(String keyword);

}

