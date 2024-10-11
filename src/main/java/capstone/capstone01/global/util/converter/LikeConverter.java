package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.like.domain.CommentLike;
import capstone.capstone01.domain.like.domain.PostLike;
import capstone.capstone01.domain.user.domain.User;

public class LikeConverter {

    public static PostLike toImagePostLike(User user, Post post) {
        return PostLike.builder()
                .user(user)
                .post(post)
                .isDeleted(false)
                .build();
    }

    public static CommentLike toCommentLike(User user, Comment comment) {
        return CommentLike.builder()
                .user(user)
                .comment(comment)
                .isDeleted(false)
                .build();
    }
}