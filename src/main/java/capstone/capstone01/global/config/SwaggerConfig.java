package capstone.capstone01.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI SchrodingerApi() {
        Info info = new Info()
                .title("캡스톤디자인 1팀 API 명세서")
                .description("백엔드 API 명세서 입니다.")
                .version("1.0.0");

        //FIXME: Spring Security 설정후 JWT를 포함하게 Config 파일 변경
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }

}
