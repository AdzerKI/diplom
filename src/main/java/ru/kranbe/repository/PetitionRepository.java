package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.petition.Petition;

public interface PetitionRepository extends JpaRepository<Petition, Long> {
}
