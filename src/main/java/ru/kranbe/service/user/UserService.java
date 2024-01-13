package ru.kranbe.service.user;

import jakarta.mail.MessagingException;
import ru.kranbe.domain.user.User;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface UserService {
    Map<String, Object> create(String email) throws MessagingException, UnsupportedEncodingException;

    User read(User user);

    User update(User user);

    void delete(String email);

    void ban(User user);

    User getById(Long id);

    User getByUsername(String username);

    User getByUsernameOrEmailOrPhone(String username);

    User getByEmail(String email);

    User getByPhone(String phone);
}
