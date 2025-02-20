package com.sky.utils;

import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class JwtUtilTest {

  private static final String SECRET_KEY = "your-secret-key-should-be-at-least-256-bits-long";

  @Test
  void shouldCreateAndParseJWTSuccessfully() {
    // Arrange
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", "1234");
    claims.put("username", "testUser");
    long ttlMillis = 3600000; // 1 hour

    // Act
    String token = JwtUtil.createJWT(SECRET_KEY, ttlMillis, claims);
    Claims parsedClaims = JwtUtil.parseJWT(SECRET_KEY, token);

    // Assert
    assertNotNull(token);
    assertEquals("1234", parsedClaims.get("userId"));
    assertEquals("testUser", parsedClaims.get("username"));
  }

  @Test
  void shouldCreateJWTWithDifferentClaims() {
    // Arrange
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", "admin");
    claims.put("email", "test@example.com");
    long ttlMillis = 3600000;

    // Act
    String token = JwtUtil.createJWT(SECRET_KEY, ttlMillis, claims);
    Claims parsedClaims = JwtUtil.parseJWT(SECRET_KEY, token);

    // Assert
    assertNotNull(token);
    assertEquals("admin", parsedClaims.get("role"));
    assertEquals("test@example.com", parsedClaims.get("email"));
  }

  @Test
  void shouldThrowExceptionForInvalidToken() {
    // Arrange
    String invalidToken = "invalid.token.string";

    // Act & Assert
    assertThrows(Exception.class, () -> JwtUtil.parseJWT(SECRET_KEY, invalidToken));
  }

  @Test
  void shouldThrowExceptionForInvalidSecretKey() {
    // Arrange
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", "1234");
    String token = JwtUtil.createJWT(SECRET_KEY, 3600000, claims);
    String wrongSecretKey = "wrong-secret-key-should-be-at-least-256-bits-long";

    // Act & Assert
    assertThrows(Exception.class, () -> JwtUtil.parseJWT(wrongSecretKey, token));
  }
}
