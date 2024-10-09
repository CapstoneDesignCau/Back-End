package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.imagepost.domain.ImagePost;
import capstone.capstone01.domain.like.domain.CommentLike;
import capstone.capstone01.domain.like.domain.ImagePostLike;
import capstone.capstone01.domain.user.domain.User;

public class LikeConverter {

    public static ImagePostLike toImagePostLike(User user, ImagePost imagePost) {
        return ImagePostLike.builder()
                .user(user)
                .imagePost(imagePost)
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