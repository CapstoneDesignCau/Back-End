package capstone.capstone01.domain.like.service;

public interface LikeService {

    void likePost(String email, Long postId);

    void cancelLikePost(String email, Long postId);

    void likeComment(String email, Long commentId);

    void cancelLikeComment(String email, Long commentId);

}