package ru.kranbe.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kranbe.domain.user.User;
import ru.kranbe.service.user.UserService;
import ru.kranbe.web.dto.user.UserDto;
import ru.kranbe.web.dto.validation.OnUpdate;
import ru.kranbe.web.mappers.UserMapper;

@RestController
@RequestMapping("/rest/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping("/update")
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public UserDto update(@Validated(OnUpdate.class)
                          @RequestBody final UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable final Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by id")
    //@PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteByEmail(@PathVariable final String email) {
        userService.delete(email);
    }
}
