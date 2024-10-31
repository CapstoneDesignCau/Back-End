package capstone.capstone01.domain.hashtag.controller;

import capstone.capstone01.domain.hashtag.dto.request.HashtagCreateRequestDto;
import capstone.capstone01.domain.hashtag.dto.response.HashtagResponseDto;
import capstone.capstone01.domain.hashtag.service.HashtagService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hashtag")
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService hashtagService;

    @Operation(summary = "해시태그 생성", description = "해시태그 생성 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public CustomApiResponse<Long> createHashtag(@RequestBody HashtagCreateRequestDto requestDto) {
        Long id = hashtagService.createHashtag(requestDto);
        return CustomApiResponse.of(SuccessStatus.HASHTAG_CREATED, id);
    }

    @Operation(summary = "모든 해시태그 조회", description = "모든 해시태그 조회 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public CustomApiResponse<List<HashtagResponseDto>> getAllHashtags() {
        List<HashtagResponseDto> hashtags = hashtagService.getAllHashtags();
        return CustomApiResponse.of(SuccessStatus.HASHTAG_OK, hashtags);
    }

    @Operation(summary = "해시태그 검색", description = "해시태그 검색 API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public CustomApiResponse<List<HashtagResponseDto>> searchHashtags(@RequestParam(required = false) String keyword) {
        List<HashtagResponseDto> hashtags;
        if (keyword == null || keyword.trim().isEmpty()) {
            hashtags = hashtagService.getAllHashtags();
        } else {
            hashtags = hashtagService.searchHashtags(keyword);
        }
        return CustomApiResponse.of(SuccessStatus.HASHTAG_OK, hashtags);
    }

}