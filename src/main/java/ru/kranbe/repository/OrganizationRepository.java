package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.organization.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
