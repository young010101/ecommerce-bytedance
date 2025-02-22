package com.sky.controller.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.client.ChatClient;

class AIControllerTest {

  @Mock private ChatClient chatClient;

  @Mock private ChatClient.Builder chatClientBuilder;

  private AIController aiController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(chatClientBuilder.build()).thenReturn(chatClient);
    aiController = new AIController(chatClientBuilder);
  }

  @Test
  void testChat() {
    // 准备测试数据
    AIController.ChatRequest request = new AIController.ChatRequest();
    request.message = "Hello AI";
    String expectedResponse = "Hello Human";

    // 配置Mock行为
    ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
    ChatClient.CallResponseSpec responseSpec = mock(ChatClient.CallResponseSpec.class);

    when(chatClient.prompt(any(String.class))).thenReturn(requestSpec);
    when(requestSpec.call()).thenReturn(responseSpec);
    when(responseSpec.content()).thenReturn(expectedResponse);

    // 执行测试
    String result = aiController.chat(request);

    // 验证结果
    assertEquals(expectedResponse, result);
  }
}
