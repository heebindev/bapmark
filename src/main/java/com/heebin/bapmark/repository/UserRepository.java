package com.heebin.bapmark.repository;

import com.heebin.bapmark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthId(String oauthId);

    Optional<User> findByEmail(String email);
}
