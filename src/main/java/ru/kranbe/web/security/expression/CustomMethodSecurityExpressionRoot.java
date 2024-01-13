// TODO remove old security
package ru.kranbe.web.security.expression;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kranbe.domain.user.Role;
import ru.kranbe.repository.RoleRepository;
import ru.kranbe.service.user.UserService;
import ru.kranbe.web.security.JwtEntity;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;
    private HttpServletRequest request;
    private UserService userService;
    private RoleRepository roleRepository;

    public CustomMethodSecurityExpressionRoot(
            final Authentication authentication
    ) {
        super(authentication);
    }

    public boolean canAccessUser(final Long id) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findByName("ROLE_USER"));

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

    @Override
    public Object getThis() {
        return target;
    }

}
