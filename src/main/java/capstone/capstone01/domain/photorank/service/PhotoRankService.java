package capstone.capstone01.domain.photorank.service;

import capstone.capstone01.domain.photorank.dto.request.PhotoRankCreateRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoRankService {
    List<Long> createPhotoRank(PhotoRankCreateRequestDto photoRankCreateRequestDto, List<MultipartFile> files);
}