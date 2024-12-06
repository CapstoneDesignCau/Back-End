package capstone.capstone01.domain.post.domain;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = false)
    private User writer;

    @Column(name = "isOpen", nullable = false)
    @Builder.Default
    private Boolean isOpen = true;

    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "postId")
    @Builder.Default
    private List<FileSaveInfo> files = new ArrayList<>();

    @Column(name = "isDeleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(name = "commentCount", nullable = false)
    @Builder.Default
    private int commentCount = 0;

    @Column(name = "likeCount", nullable = false)
    @Builder.Default
    private int likeCount = 0;

    public void update(String title) {
        this.title = title;
    }

    public void delete(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
        this.commentCount +=1;
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        this.commentCount -= 1;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }

    public void addFile(FileSaveInfo file) {
        files.add(file);
    }

    public void removeFile(FileSaveInfo file) {
        files.remove(file);
    }
}