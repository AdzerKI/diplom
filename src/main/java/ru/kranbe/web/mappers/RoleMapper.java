package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.user.Role;
import ru.kranbe.web.dto.user.RoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper extends Mappable<Role, RoleDTO> {
}
