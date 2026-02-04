package com.freela.freelancer.presentation.share;

public record ResponseDefault(
        Boolean success,
        String message,
        Object data
) {}