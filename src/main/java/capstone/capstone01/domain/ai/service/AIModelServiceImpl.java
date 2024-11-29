package capstone.capstone01.domain.ai.service;

import capstone.capstone01.domain.ai.dto.request.ImageEvaluationRequest;
import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class AIModelServiceImpl implements AIModelService {

    private final RestTemplate restTemplate;

    @Override
    public EvaluateResultResponseDto evaluateImage(Long imageEvaluationId, String imageUrl) {
        String url = "http://localhost:8000/evaluate_image"; // FastAPI 서버 URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ImageEvaluationRequest request = new ImageEvaluationRequest(imageUrl, imageEvaluationId);
        HttpEntity<ImageEvaluationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<EvaluateResultResponseDto> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, EvaluateResultResponseDto.class);

        EvaluateResultResponseDto responseBody = response.getBody();
        log.info("Received response from FastAPI: {}", responseBody);

        return responseBody;
    }
}