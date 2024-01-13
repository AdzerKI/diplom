package ru.kranbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kranbe.domain.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
