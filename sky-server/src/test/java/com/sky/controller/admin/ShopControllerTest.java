package com.sky.controller.admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sky.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

class ShopControllerTest {

  @InjectMocks private ShopController shopController;

  @Mock private RedisTemplate<String, Object> redisTemplate;

  @Mock private ValueOperations<String, Object> valueOperations;

  private static final String KEY = "SHOP_STATUS";

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(redisTemplate.opsForValue()).thenReturn(valueOperations);
  }

  @Test
  void testSetStatusToOpen() {
    // 测试设置店铺为营业状态
    doNothing().when(valueOperations).set(KEY, 1);

    Result<?> result = shopController.status(1);

    verify(valueOperations).set(KEY, 1);
    assertNotNull(result);
    assertEquals(1, result.getCode());
  }

  @Test
  void testSetStatusToClose() {
    // 测试设置店铺为打烊状态
    doNothing().when(valueOperations).set(KEY, 0);

    Result<?> result = shopController.status(0);

    verify(valueOperations).set(KEY, 0);
    assertNotNull(result);
    assertEquals(1, result.getCode());
  }

  @Test
  void testGetStatus() {
    // 测试获取店铺状态
    when(valueOperations.get(KEY)).thenReturn(1);

    Result<Integer> result = shopController.getStatus();

    verify(valueOperations).get(KEY);
    assertNotNull(result);
    assertEquals(1, result.getData());
  }
}
