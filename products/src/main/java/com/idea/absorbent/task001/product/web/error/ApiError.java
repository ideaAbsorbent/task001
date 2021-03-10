package com.idea.absorbent.task001.product.web.error;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiError {

    String title;
    String status;
    String detail;

    public ApiError(String title, String status, String detail) {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public Map<String, Object> getResponseBody() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("title", this.title);
        body.put("status", this.status);
        body.put("details", this.detail);
        return  body;
    }
}