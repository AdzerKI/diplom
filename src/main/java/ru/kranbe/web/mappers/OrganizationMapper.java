package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.organization.Organization;
import ru.kranbe.web.dto.organization.OrganizationDto;

@Mapper(componentModel = "spring")
public interface OrganizationMapper extends Mappable<Organization, OrganizationDto> {
}
