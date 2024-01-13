package ru.kranbe.service.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RandomPassword {
    private final PasswordEncoder passwordEncoder;

    public String[] setEncodedPassword(int len) {
        String password = alphaNumericString(len);
        String encodedPassword = passwordEncoder.encode(password);
        return new String[]{password, encodedPassword};
    }

    private String alphaNumericString(int len) { //генератор пароля
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
