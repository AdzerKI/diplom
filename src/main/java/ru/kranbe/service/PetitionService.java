package ru.kranbe.service;


import ru.kranbe.domain.petition.Petition;

public interface PetitionService {
    Petition create(Petition petition);

    Petition update(Petition petition);

    void delete(Long id);

    Petition getById(Long id);
}
