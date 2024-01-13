package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.crane.Crane;
import ru.kranbe.web.dto.crane.CraneDTO;

@Mapper(componentModel = "spring")
public interface CraneMapper extends Mappable<Crane, CraneDTO> {
}
