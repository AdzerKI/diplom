package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.user.verification.VerificationEntity;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationEntity, Long> {
    Optional<VerificationEntity> findByEmail(String email);

    Optional<VerificationEntity> findByToken(String token);
}
