package capstone.capstone01.domain.photorank.service;

import capstone.capstone01.domain.photorank.domain.PhotoRank;
import capstone.capstone01.domain.photorank.domain.repository.PhotoRankRepository;
import capstone.capstone01.domain.photorank.dto.request.PhotoRankCreateRequestDto;
import capstone.capstone01.domain.photorank.dto.request.PhotoRankUpdateRequestDto;
import capstone.capstone01.domain.photorank.dto.response.PhotoRankResponseDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.PhotoRankException;
import capstone.capstone01.global.util.converter.PhotoRankConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoRankServiceImpl implements PhotoRankService {

    private final PhotoRankRepository photoRankRepository;
    private final StorageService fileSaveInfoService; // Assuming you have a service to handle file saving

    @Override
    public List<Long> createPhotoRank(PhotoRankCreateRequestDto photoRankCreateRequestDto, List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Files are required");
        }

        List<FileSaveInfo> savedFiles = fileSaveInfoService.saveFileList(files, FileCategory.PHOTO_RANK);

        List<Long> photoRankIds = new ArrayList<>();
        for (FileSaveInfo fileSaveInfo : savedFiles) {
            PhotoRank photoRank = PhotoRankConverter.toPhotoRank(photoRankCreateRequestDto, fileSaveInfo);
            photoRankRepository.save(photoRank);
            photoRankIds.add(photoRank.getId());
        }

        return photoRankIds;
    }

    @Override
    public void updatePhotoRankCounts(PhotoRankUpdateRequestDto photoRankUpdateRequestDto) {
        List<Long> appearanceIds = photoRankUpdateRequestDto.getAppearanceIds();
        Long selectedId = photoRankUpdateRequestDto.getSelectedId();

        for (Long id : appearanceIds) {
            PhotoRank photoRank = photoRankRepository.findById(id)
                    .orElseThrow(() -> new PhotoRankException(ErrorStatus.PHOTO_RANK_NOT_FOUND));
            photoRank.setAppearanceCount(photoRank.getAppearanceCount() + 1);
            photoRankRepository.save(photoRank);
        }

        if (selectedId != null) {
            PhotoRank selectedPhotoRank = photoRankRepository.findById(selectedId)
                    .orElseThrow(() -> new PhotoRankException(ErrorStatus.PHOTO_RANK_NOT_FOUND));
            selectedPhotoRank.setSelectedCount(selectedPhotoRank.getSelectedCount() + 1);
            photoRankRepository.save(selectedPhotoRank);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotoRankResponseDto> getPhotoRanks() {
        LocalDate today = LocalDate.now();
        List<PhotoRank> photoRanks = photoRankRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today, PageRequest.of(0, 16));
        return photoRanks.stream()
                .map(PhotoRankConverter::toPhotoRankResponseDto)
                .collect(Collectors.toList());
    }


}