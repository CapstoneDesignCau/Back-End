package capstone.capstone01.domain.imagepost.domain;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImagePost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagePostId")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = false)
    private User writer;

    //TODO: file_save_info 필드에 추가(중간고사 이후 작업 예정)

    @OneToMany(mappedBy = "imagePost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "isDeleted")
    @Builder.Default
    private Boolean isDeleted = false;

    public void update(String title) {
        this.title = title;
    }

    public void delete(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setImagePost(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
}
