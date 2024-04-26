package uz.pdp.railwayticketproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.railwayticketproject.dto.auth.SetMailDto;
import uz.pdp.railwayticketproject.entity.VerificationCode;
import uz.pdp.railwayticketproject.repository.VerificationCodeRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final VerificationCodeRepository verificationCodeRepository;

    private final Random rand = new Random();


    public ResponseEntity<?> sendEmail(SetMailDto setMailDto) {

        String code = generateRandomCode();
        saveVerificationCode(setMailDto.getTo(), code);
        sendVerificationCodeEmail(setMailDto.getTo(), code);

        return ResponseEntity.ok("Email sent successfully!");
    }

    private String generateRandomCode() {

        int code = rand.nextInt(99999, 1000000);

        return String.valueOf(code);
    }


    private void saveVerificationCode(String email, String code) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        verificationCode.setCreatedDate(LocalDateTime.now());
        verificationCodeRepository.save(verificationCode);
    }

    private void sendVerificationCodeEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verification Code");
        message.setText("Your verification code is: " + code);
        mailSender.send(message);
    }

    @Scheduled(fixedDelay = 3600000)
    public void deleteExpiredCodes() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        verificationCodeRepository.deleteByCreatedDateBefore(oneHourAgo);
    }

}