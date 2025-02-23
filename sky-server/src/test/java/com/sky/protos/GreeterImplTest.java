package com.sky.protos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GreeterImplTest {

  private GreeterImpl greeter;

  @BeforeEach
  void setUp() {
    greeter = new GreeterImpl();
  }

  @Test
  void greet_ShouldReturnCorrectGreetingMessage() {
    // Arrange
    String testName = "Alice";
    GreeterRequest request = GreeterRequest.newBuilder().setName(testName).build();

    // Act
    GreeterReply response = greeter.greet(request);

    // Assert
    assertNotNull(response);
    assertEquals("hello," + testName, response.getMessage());
  }

  @Test
  void greet_ShouldHandleEmptyName() {
    // Arrange
    GreeterRequest request = GreeterRequest.newBuilder().setName("").build();

    // Act
    GreeterReply response = greeter.greet(request);

    // Assert
    assertNotNull(response);
    assertEquals("hello,", response.getMessage());
  }
}
