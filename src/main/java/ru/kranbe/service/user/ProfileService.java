package ru.kranbe.service.user;

import ru.kranbe.domain.user.Profile;

public interface ProfileService {

    Profile create();

    Profile update(Profile profile);

    void delete(Long id);

    Profile getById(Long id);
}
