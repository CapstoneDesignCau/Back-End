package capstone.capstone01.domain.like.service;

public interface LikeService {

    void likeImagePost(String email, Long imagePostId);

    void cancelLikeImagePost(String email, Long imagePostId);

    void likeComment(String email, Long commentId);

    void cancelLikeComment(String email, Long commentId);

}