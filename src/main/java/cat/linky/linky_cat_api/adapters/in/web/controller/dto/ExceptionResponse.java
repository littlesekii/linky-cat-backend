package cat.linky.linky_cat_api.adapters.in.web.controller.dto;

import java.time.Instant;

public record ExceptionResponse(
    Instant moment,
    Integer statusCode,
    String error,
    String errorCode,
    String message,
    String path
) {}
