package capstone.capstone01.domain.comment.domain;

import capstone.capstone01.domain.imagepost.domain.ImagePost;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(targetEntity = ImagePost.class)
    @JoinColumn(name = "imagePostId", nullable = false)
    private ImagePost imagePost;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = false)
    private User writer;

    @Column(name = "isDeleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void update(String content) {
        this.content = content;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void setImagePost(ImagePost imagePost) {
        this.imagePost = imagePost;
    }

}
