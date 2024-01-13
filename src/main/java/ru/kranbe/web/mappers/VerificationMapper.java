package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.user.verification.VerificationEntity;
import ru.kranbe.web.dto.user.VerificationDTO;

@Mapper(componentModel = "spring")
public interface VerificationMapper extends Mappable<VerificationEntity, VerificationDTO> {
}
