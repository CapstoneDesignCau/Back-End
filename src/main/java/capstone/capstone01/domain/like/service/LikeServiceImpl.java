package capstone.capstone01.domain.like.service;

import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Override
    public void likeImagePost(String email, String imagePostId) {
        // 이미지 게시글에 좋아요를 누르는 로직 구현
    }

    @Override
    public void cancelLikeImagePost(String email, String imagePostId) {
        // 이미지 게시글에 좋아요를 취소하는 로직 구현
    }

    @Override
    public void likeComment(String email, String commentId) {
        // 댓글에 좋아요를 누르는 로직 구현
    }

    @Override
    public void cancelLikeComment(String email, String commentId) {
        // 댓글에 좋아요를 취소하는 로직 구현
    }
}