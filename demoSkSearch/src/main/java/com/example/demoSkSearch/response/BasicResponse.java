package com.example.demoSkSearch.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse {

    public static final String OK = "OK";
    public static final String NOK = "NOK";

    private String status;
    private String reason;
    private Map<String, Object> result;
}
