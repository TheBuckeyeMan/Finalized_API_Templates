package com.example.lambdatemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.lambdatemplate.api.Handler.LambdaHandler;

public class AppTest {

    @Test
    public void handleRequest_shouldReturnConstantValue() {
        LambdaHandler function = new LambdaHandler();
        Object result = function.handleRequest("echo", null);
        assertEquals("echo", result);
    }
}
