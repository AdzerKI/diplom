package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.user.Profile;
import ru.kranbe.web.dto.user.ProfileDTO;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends Mappable<Profile, ProfileDTO> {
}
