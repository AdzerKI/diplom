package ru.kranbe.service.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NextUsername {
    public String setNextUsername(long id) {
        String number = String.valueOf(id);
        StringBuilder sb = new StringBuilder();
        sb.append("u");

        for (int i = 7; i > number.length(); i--) {
            if (number.length() + 1 == i){
                sb.append(number);
            } else {
                sb.append("0");
            }
        }

        return sb.toString();
    }
}
