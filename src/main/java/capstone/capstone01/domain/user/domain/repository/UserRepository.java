package capstone.capstone01.domain.user.domain.repository;

import capstone.capstone01.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}