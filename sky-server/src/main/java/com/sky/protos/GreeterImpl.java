package com.sky.protos;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Implementation of the Greeter service.
 */
@Slf4j
@DubboService
public final class GreeterImpl extends DubboGreeterTriple.GreeterImplBase {
    /**
     * Handles the greet request and returns a greeting message.
     *
     * @param request The greeting request containing the name
     * @return A greeting reply containing the message
     */
    @Override
    public GreeterReply greet(final GreeterRequest request) {
        String serverName = "HelloWorld";
        log.info("Server {} received greet request {}", serverName, request);
        return GreeterReply.newBuilder()
                .setMessage("hello," + request.getName())
                .build();
    }
}
