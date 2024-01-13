package ru.kranbe.web.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.user.Role;
import ru.kranbe.repository.RoleRepository;
import ru.kranbe.service.user.UserService;
import ru.kranbe.web.security.JwtEntity;

import java.util.*;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public boolean canAccessUser(final Long id) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findByName("ROLE_USER"));

        return userId.equals(id) || hasAnyRole(authentication, userRoles);
    }

    public boolean canAccessAdmin(final Long id) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findByName("ROLE_ADMIN"));

        return userId.equals(id) || hasAnyRole(authentication, userRoles);
    }

    private boolean hasAnyRole(final Authentication authentication,
                               final Set<Role> roles) {
        for (Role role : roles) {
            SimpleGrantedAuthority authority
                    = new SimpleGrantedAuthority(role.getName());
            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }

//    public boolean canAccessTask(final Long taskId) {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();
//
//        JwtEntity user = (JwtEntity) authentication.getPrincipal();
//        Long id = user.getId();
//
//        return userService.isTaskOwner(id, taskId);
//    }

}
