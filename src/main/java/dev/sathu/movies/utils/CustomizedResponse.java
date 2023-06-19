package dev.sathu.movies.utils;

import java.util.HashMap;
import java.util.Map;

public class CustomizedResponse {
    public static Map<String, Object> buildResponse(Object data, String status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("data", data);

        return response;
    }
}
