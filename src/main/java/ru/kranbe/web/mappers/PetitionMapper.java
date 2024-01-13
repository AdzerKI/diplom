package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.petition.Petition;
import ru.kranbe.web.dto.petition.PetitionDto;

@Mapper(componentModel = "spring")
public interface PetitionMapper extends Mappable<Petition, PetitionDto> {
}
