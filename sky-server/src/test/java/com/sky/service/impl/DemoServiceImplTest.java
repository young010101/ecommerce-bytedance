package com.sky.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DemoServiceImplTest {

  private final DemoServiceImpl demoService = new DemoServiceImpl();

  @Test
  void sayHello_ShouldReturnExpectedMessage() {
    // Arrange
    String name = "张三";
    String expected = "Hello 张三, response from provider.";

    // Act
    String result = demoService.sayHello(name);

    // Assert
    assertEquals(expected, result);
  }
}
