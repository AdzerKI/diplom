package ru.kranbe.service.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExpireDate {
    public Date calculateExpiryDate(int expiryTimeInHours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.HOUR, expiryTimeInHours);
        return new Date(cal.getTime().getTime());
    }
}
