package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import capstone.capstone01.domain.hashtag.dto.response.HashtagResponseDto;

public class HashtagConverter {

    public static Hashtag toHashtag(String name) {
        return Hashtag.builder()
                .hashtag(name)
                .build();
    }

    public static HashtagResponseDto toHashtagResponseDto(Hashtag hashtag) {
        return HashtagResponseDto.builder()
                .id(hashtag.getId())
                .hashtag(hashtag.getHashtag())
                .build();
    }

}