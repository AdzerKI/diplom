package ru.kranbe.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.organization.Organization;
import ru.kranbe.service.OrganizationService;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public Organization create(Organization organization) {
        return null;
    }

    @Override
    public Organization update(Organization organization) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Organization getById(Long id) {
        return null;
    }
}
