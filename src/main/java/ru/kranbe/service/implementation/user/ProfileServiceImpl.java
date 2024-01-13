package ru.kranbe.service.implementation.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.exception.ResourceNotFoundException;
import ru.kranbe.domain.user.Profile;
import ru.kranbe.repository.ProfileRepository;
import ru.kranbe.service.user.ProfileService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Profile create() {
        Profile profile = new Profile();
        profile.setStatus(Status.ACTIVE);
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public Profile update(Profile profile) {
        // TODO
        return profileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        // TODO "DELETED"
        profileRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found."));
    }
}
