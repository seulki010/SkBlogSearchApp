package com.example.demoSkSearch.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    public static int MISSING_PARAM = 1;
    public static int INTERNAL_ERR = 2;

    private int code;
    private String message;
}
