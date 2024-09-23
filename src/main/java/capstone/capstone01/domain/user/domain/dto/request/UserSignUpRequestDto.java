package capstone.capstone01.domain.user.domain.dto.request;

import capstone.capstone01.domain.user.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto {

    @Email(message = "유효한 이메일 주소를 입력하세요.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Schema(description = "유저의 이메일 주소", example = "puang@cau.ac.kr")
    private String email;

    @NotBlank(message = "패스워드는 필수 입력 항목입니다.")
    @Size(min = 8, max = 16, message = "패스워드는 8자 이상 16자 이하로 입력해야 합니다.")
    @Schema(description = "유저의 패스워드", example = "password123!")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Size(max = 30, message = "이름은 최대 30자까지 입력할 수 있습니다.")
    @Schema(description = "유저의 이름", example = "puang")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(max = 8, message = "닉네임은 최대 8자까지 입력할 수 있습니다.")
    @Schema(description = "유저의 닉네임", example = "puang123")
    private String nickname;

    @NotNull(message = "성별은 필수 입력 항목입니다.")
    @Schema(description = "유저의 성별", example = "MALE")
    private Gender gender;

    @NotNull(message = "생년월일은 필수 입력 항목입니다.")
    @Past(message = "유효한 생년월일을 입력하세요.")
    @Schema(description = "유저의 생년월일", example = "2015-01-01")
    private LocalDate birthday;

}