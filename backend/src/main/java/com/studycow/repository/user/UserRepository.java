package com.studycow.repository.user;

import com.studycow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String email);
    Optional<User> findByUserNickname(String userNickName);
}
