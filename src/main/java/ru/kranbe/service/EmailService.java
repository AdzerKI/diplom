package ru.kranbe.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface EmailService {
    void sendEmail(final String toAddress, final String subject,
                   final String templateLocation, final Map<String, Object> variables)
            throws MessagingException, UnsupportedEncodingException;
}
