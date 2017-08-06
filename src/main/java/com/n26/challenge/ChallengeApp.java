package com.n26.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

@SpringBootApplication
public class ChallengeApp {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApp.class, args);
    }
}
