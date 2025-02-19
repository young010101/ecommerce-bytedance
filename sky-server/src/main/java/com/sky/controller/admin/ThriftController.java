package com.sky.controller.admin;

import com.sky.result.Result;

import com.sky.thrift.Hello;
import com.sky.thrift.Request;
import com.sky.thrift.Response;
import lombok.extern.slf4j.Slf4j;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/thrift")
public class ThriftController {

    @GetMapping("/hello")
    Result<String> thrift_hello() {
        TTransport transport;
        String name;
        {
            try {
                transport = new TSocket("localhost", 8888);
            } catch (TTransportException ex) {
                throw new RuntimeException(ex);
            }
            TProtocol protocol = new TBinaryProtocol(transport);
            try {
                transport.open();
            } catch (TTransportException ex) {
                throw new RuntimeException(ex);
            }
            Hello.Client client = new Hello.Client(protocol);
            // AddRequest request = new AddRequest();
            Request request = new Request();
            request.message = "hello world2";
            try {
                Response response = client.echo(request);
                name = response.message;
            } catch (TException ex) {
                throw new RuntimeException(ex);
            }
            log.info("thrift user: {}", request.message);
        }
        return Result.success("hello " + name + "!");
    }

    @GetMapping("/hello/{name}")
    Result<String> thrift(@PathVariable String name) {
        log.info("thrift hello: {}", name);
        return Result.success("hello " + name + "!");
    }
}
