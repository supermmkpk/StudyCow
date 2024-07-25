package com.studycow.repository.user;

import com.studycow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 유저 저장소
 * <pre>
 *     Spring Data Jpa를 활용한 User Repository
 * </pre>
 * @author 채기훈
 * @since JDK17
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String email);
    Optional<User> findByUserNickname(String userNickName);
}
