package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.photorank.domain.PhotoRank;
import capstone.capstone01.domain.photorank.dto.request.PhotoRankCreateRequestDto;
import capstone.capstone01.domain.photorank.dto.response.PhotoRankResponseDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.dto.response.FileResponseDto;

public class PhotoRankConverter {

    public static PhotoRank toPhotoRank(PhotoRankCreateRequestDto requestDto, FileSaveInfo fileSaveInfo) {
        return PhotoRank.builder()
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .appearanceCount(0)
                .selectedCount(0)
                .fileSaveInfo(fileSaveInfo)
                .build();
    }

    public static PhotoRankResponseDto toPhotoRankResponseDto(PhotoRank photoRank) {
        FileResponseDto fileResponseDto = FileResponseDto.builder()
                .fileUrl(photoRank.getFileSaveInfo().getFileUrl())
                .fileName(photoRank.getFileSaveInfo().getOriginFileName())
                .extension(photoRank.getFileSaveInfo().getExtension())
                .build();

        return PhotoRankResponseDto.builder()
                .id(photoRank.getId())
                .appearanceCount(photoRank.getAppearanceCount())
                .selectedCount(photoRank.getSelectedCount())
                .fileResponse(fileResponseDto)
                .build();
    }
}