package com.sky.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationConfigTest {

  @Autowired private Environment environment;

  @Test
  void testRequiredConfigsExist() {
    assertNotNull(environment.getProperty("sky.datasource.driver-class-name"));
    assertNotNull(environment.getProperty("sky.datasource.host"));
    assertNotNull(environment.getProperty("sky.datasource.port"));
  }

  @Test
  void testConfigFormat() {
    assertFalse(StringUtils.isEmpty(environment.getProperty("sky.datasource.port")));
    assertFalse(StringUtils.isEmpty(environment.getProperty("sky.redis.port")));
  }
}
