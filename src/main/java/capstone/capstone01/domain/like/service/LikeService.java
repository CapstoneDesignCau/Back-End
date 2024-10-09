package capstone.capstone01.domain.like.service;

public interface LikeService {

    void likeImagePost(String email, String imagePostId);

    void cancelLikeImagePost(String email, String imagePostId);

    void likeComment(String email, String commentId);

    void cancelLikeComment(String email, String commentId);

}