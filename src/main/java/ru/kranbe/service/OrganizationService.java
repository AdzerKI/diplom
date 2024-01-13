package ru.kranbe.service;

import ru.kranbe.domain.organization.Organization;

public interface OrganizationService {
    Organization create(Organization organization);

    Organization update(Organization organization);

    void delete(Long id);

    Organization getById(Long id);
}
