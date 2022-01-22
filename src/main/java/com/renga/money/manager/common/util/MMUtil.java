package com.renga.money.manager.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MMUtil {

    public static final BCryptPasswordEncoder B_CRYPT_ENCODER =  new BCryptPasswordEncoder();

    private static final ZoneId NY_ZONE = ZoneId.of("America/New_York");

    public static LocalDateTime getLocalDateTimeNYZone(){
        return Instant.now().atZone(NY_ZONE).toLocalDateTime();
    }

    public static LocalDate getLocalDateNYZone(){
        return Instant.now().atZone(NY_ZONE).toLocalDate();
    }
}
