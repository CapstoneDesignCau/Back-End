package capstone.capstone01.global.util.value;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StaticValue {

    @Value("${default.profile.image.url}")
    private String defaultProfileImageUrl;

    public static String DEFAULT_PROFILE_IMAGE_URL; // 기본 프로필 이미지 주소

    public static final Integer DEFAULT_PAGE_SIZE = 10; // 각 페이지가 반환하는 개수

    public static final int TOP_POSTS_LIMIT = 3; // 좋아요를 가장 많이 받은 게시글 반환 개수

    public static final int RECENT_DAYS = 7; // 최근 기간의 길이

    //static 변수에 @Value 를 바로 사용시 올바르게 값이 주입되지 않기 때문에 우회하여 주입.
    @PostConstruct
    private void init() {
        DEFAULT_PROFILE_IMAGE_URL = this.defaultProfileImageUrl;
    }

}