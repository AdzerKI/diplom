package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> deleteByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findByUsernameOrEmailOrPhone (String username, String email, String phone);
}
