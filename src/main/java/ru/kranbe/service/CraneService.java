package ru.kranbe.service;


import ru.kranbe.domain.crane.Crane;
import ru.kranbe.web.dto.crane.open.RequestPropertyListByType;
import ru.kranbe.web.dto.crane.open.ResponseGetCraneType;
import ru.kranbe.web.dto.crane.open.ResponsePropertyListByType;

import java.util.Set;

public interface CraneService {
    Crane create(Crane crane, Long userId);

    Crane update(Crane crane);

    void delete(Long id);

    Crane getById(Long id);

    ResponsePropertyListByType getPropertiesByType(RequestPropertyListByType request);

    ResponseGetCraneType getCraneType();
}
