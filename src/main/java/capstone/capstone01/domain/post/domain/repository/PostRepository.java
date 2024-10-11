package capstone.capstone01.domain.post.domain.repository;

import capstone.capstone01.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
