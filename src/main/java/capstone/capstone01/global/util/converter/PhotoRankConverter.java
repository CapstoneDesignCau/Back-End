package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.photorank.domain.PhotoRank;
import capstone.capstone01.domain.photorank.dto.request.PhotoRankCreateRequestDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;

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

}