package com.example.data_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    private JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void testGenerateToken() {
        // when
        String token = jwtUtil.generateToken("test@example.com");

        // then
        assertTrue(token != null && token.length() > 0);
    }

    @Test
    public void testExtractEmail() {
        // given
        String token = jwtUtil.generateToken("test@example.com");

        // when
        String email = jwtUtil.extractEmail(token);

        // then
        assertEquals("test@example.com", email);
    }
}