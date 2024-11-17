package capstone.capstone01.domain.photorank.controller;

import capstone.capstone01.domain.photorank.dto.request.PhotoRankCreateRequestDto;
import capstone.capstone01.domain.photorank.service.PhotoRankService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/photoRank")
@RequiredArgsConstructor
public class PhotoRankController {

    private final PhotoRankService photoRankService;

    @Operation(summary = "사진 랭크 생성", description = "사진 랭크 생성 API (ADMIN 용)")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public CustomApiResponse<List<Long>> createPhotoRank(
            @Parameter(description = "사진 랭크 정보", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhotoRankCreateRequestDto.class)))
            @Valid @RequestPart("photoRank") PhotoRankCreateRequestDto photoRankCreateRequestDto,
            @Parameter(description = "파일 목록", content = @Content(mediaType = "multipart/form-data"))
            @RequestPart("files") List<MultipartFile> files
    ) {
        List<Long> photoRankIds = photoRankService.createPhotoRank(photoRankCreateRequestDto, files);
        return CustomApiResponse.of(SuccessStatus.PHOTO_RANK_CREATED, photoRankIds);
    }


}