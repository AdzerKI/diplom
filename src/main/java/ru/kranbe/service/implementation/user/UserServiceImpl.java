package ru.kranbe.service.implementation.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.exception.ResourceNotFoundException;
import ru.kranbe.domain.user.Role;
import ru.kranbe.domain.user.User;
import ru.kranbe.service.EmailService;
import ru.kranbe.service.helper.RandomPassword;
import ru.kranbe.repository.RoleRepository;
import ru.kranbe.repository.UserRepository;
import ru.kranbe.service.user.ProfileService;
import ru.kranbe.service.user.UserService;
import ru.kranbe.service.helper.NextUsername;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileService profileService;
    private final NextUsername nextUsername;
    private final RandomPassword randomPassword;
    private final EmailService emailService;

    // TODO фильтрация возврата пользователя STATUS="ACTIVE"
    @Override
    @Transactional(readOnly = true)
    public User getById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User by id not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User by username not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User by email not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByPhone(final String phone) {
        return userRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User by phone not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsernameOrEmailOrPhone(final String request) {
        User user = userRepository.findByUsernameOrEmailOrPhone(request, request, request)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
//        User user = this.getByEmail(request);
//        if (user == null) user = this.getByUsername(request);
//        if (user == null) user = this.getByPhone(request);
//        if (user == null) throw new ResourceNotFoundException("Пользователь не найден");

        return user;
    }

    @Override
    @Transactional
    public Map<String, Object> create(String email)
            throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("Email already used.");
        }

        // Пароль [0] - plain, [1] - encoded
        String[] password = randomPassword.setEncodedPassword(10);

        // TODO getNextKey в одно действие надо сделать
        User user = new User();
        user.setEmail(email);
        user.setProfile(profileService.create()); // Профиль
        Set<Role> roles = new HashSet<>(Set.of(roleRepository.findByName("ROLE_USER")));
        user.setRoles(roles);// Роль
        user.setPassword(password[1]);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user); // SAVE

        // генерим username
        String login = nextUsername.setNextUsername(registeredUser.getId());
        registeredUser.setUsername(login);
        userRepository.save(registeredUser); // SAVE

        // Для письма пользователю поля
        Map<String, Object> variables = Map.of("username", login,"password", password[0]);

        emailService.sendEmail(email, "Ваши данные для входа",
                "html/mail/auth/registration_finish.html", variables);

        // TODO notification "Добро пожаловать, заполните персональные данные" переход в профиль
        return variables;
    }

    @Override
    @Transactional
    public User read(final User user) {
        // TODO show user
        return null;
    }


    @Override
    @Transactional
    public User update(final User user) {
        // TODO update user + profile
        return user;
    }

    // TODO ответ удален
    @Override
    @Transactional
    public void delete(final String email) {
//        user.setStatus(Status.DELETED);
//        userRepository.save(user); // SAVE

//        log.info("IN delete - user: {} successfully deleted", user);
        userRepository.deleteByEmail(email);
    }

    // TODO ответ забанен
    @Override
    @Transactional
    public void ban(final User user) {
        user.setStatus(Status.BANNED);
        userRepository.save(user); // SAVE

        log.info("IN ban - user: {} successfully banned", user);
    }
}
