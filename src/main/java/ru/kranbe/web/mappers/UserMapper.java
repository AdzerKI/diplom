package ru.kranbe.web.mappers;

import org.mapstruct.Mapper;
import ru.kranbe.domain.user.User;
import ru.kranbe.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
