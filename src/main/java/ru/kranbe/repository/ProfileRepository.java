package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.user.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
