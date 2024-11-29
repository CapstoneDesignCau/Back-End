//package capstone.capstone01.domain.ai.service;
//
//
//import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
//import capstone.capstone01.domain.ai.dto.response.FeedbackDto;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AIModelServiceMock implements AIModelService {
//
//    @Override
//    public EvaluateResultResponseDto evaluateImage(Long imageEvaluationId, String imageUrl) {
//        // Mock response
//        EvaluateResultResponseDto response = EvaluateResultResponseDto.builder()
//                .totalScore(90)
//                .feedback(List.of(
//                        FeedbackDto.builder().id(1).message("아웃포커싱 효과를 활용하면 인물을 더 돋보이게 할 수 있습니다.").build(),
//                        FeedbackDto.builder().id(2).message("카메라를 아래쪽에서 찍어 보세요.").build(),
//                        FeedbackDto.builder().id(3).message("사진이 너무 어둡습니다. 명도를 올려보세요.").build()
//                ))
//                .moreInfo("전신 세로 길이: 25 pixels, 얼굴 세로 길이: 10 pixels, 등신 비율: 7 등신")
//                .build();
//        return response;
//    }
//}