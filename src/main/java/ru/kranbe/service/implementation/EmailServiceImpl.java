package ru.kranbe.service.implementation;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.kranbe.service.EmailService;
import ru.kranbe.service.properties.EmailProperties;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailProperties emailProperties;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(String toAddress, String subject,
                          String templateLocation, Map<String, Object> variables)
            throws MessagingException, UnsupportedEncodingException {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost(emailProperties.getHost());
        sender.setPort(emailProperties.getPort());
        sender.setUsername(emailProperties.getUsername());
        sender.setPassword(emailProperties.getPassword());

        Properties prop = new Properties();
        //prop.setProperty("mail.debug", "true");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");

        // Сборка письма из шаблона thymeleaf
        Context context = new Context();
        context.setVariables(variables);
        String emailContent = templateEngine.process(templateLocation, context);

        sender.setJavaMailProperties(prop);

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
        helper.setFrom("messages@kranbe.ru", "КранБеру");
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(emailContent, true);
//        helper.addAttachment("attachement",
//                new ByteArrayResource(IOUtils.toByteArray(inputStream)));

        sender.send(message);
    }
}
