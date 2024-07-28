package com.studycow.repository.user;

import com.studycow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 유저 Repository
 * <pre>
 *     유저 저장을 위한 Spring Data JPA
 * </pre>
 * @author 채기훈
 * @since JDK17
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String email);
    Optional<User> findByUserNickname(String userNickName);
    Optional<List<User>>findByUserNicknameContainingIgnoreCase(String userNickName);
}
