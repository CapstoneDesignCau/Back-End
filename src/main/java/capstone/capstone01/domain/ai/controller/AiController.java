package capstone.capstone01.domain.ai.controller;

import capstone.capstone01.domain.ai.dto.response.EvaluateResultResponseDto;
import capstone.capstone01.domain.ai.service.AIModelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AIModelService aiModelService;

    @Operation(summary = "이미지 평가", description = "이미지 평가 API (ADMIN 용)")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/evaluate")
    public EvaluateResultResponseDto evaluateImage(
            @RequestParam Long imageEvaluationId,
            @RequestParam String imageUrl
    ) {
        return aiModelService.evaluateImage(imageEvaluationId, imageUrl);
    }

}