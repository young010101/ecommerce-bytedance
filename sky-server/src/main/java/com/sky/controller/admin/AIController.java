package com.sky.controller.admin;

import com.sky.properties.ProxyProperties;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

  private final ChatClient chatClient;

  public AIController(ProxyProperties proxy, ChatClient.Builder builder) {

    System.setProperty("https.proxyHost", proxy.getHost());
    System.setProperty("https.proxyPort", proxy.getPort());
    this.chatClient = builder.build();
  }

  public static class ChatRequest {
    public String message;
  }

  @PostMapping("/chat")
  public String chat(@RequestBody ChatRequest request) {
    return chatClient.prompt(request.message).call().content();
  }
}
