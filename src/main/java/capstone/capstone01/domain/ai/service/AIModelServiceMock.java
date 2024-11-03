package capstone.capstone01.domain.ai.service;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import capstone.capstone01.domain.ai.dto.response.PartScoreDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AIModelServiceMock implements AIModelService {
    // 동작 확인용 Mock Class

    @Override
    public EvaluateResultResponseDto evaluateImage(Long imageEvaluationId, MultipartFile imageFile) {

        PartScoreDto partScore1 = PartScoreDto.builder()
                .score(20)
                .reason("사진의 구도가 좋습니다.")
                .build();

        PartScoreDto partScore2 = PartScoreDto.builder()
                .score(15)
                .reason("색감이 좋습니다.")
                .build();

        PartScoreDto partScore3 = PartScoreDto.builder()
                .score(10)
                .reason("조명이 좋습니다.")
                .build();

        return EvaluateResultResponseDto.builder()
                .imageEvaluationId(imageEvaluationId)
                .totalScore(45)
                .partScores(List.of(partScore1, partScore2, partScore3))
                .build();
    }
}