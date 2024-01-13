package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.crane.Crane;

public interface CraneRepository extends JpaRepository<Crane, Long> {
}
