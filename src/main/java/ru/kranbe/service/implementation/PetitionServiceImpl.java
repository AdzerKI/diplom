package ru.kranbe.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.petition.Petition;
import ru.kranbe.service.PetitionService;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetitionServiceImpl implements PetitionService {
    @Override
    public Petition create(Petition petition) {
        return null;
    }

    @Override
    public Petition update(Petition petition) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Petition getById(Long id) {
        return null;
    }
}
