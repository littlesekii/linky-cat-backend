package cat.linky.linky_cat_api.core.ports.in.dto.profile;

public record ProfileUpdateImageCommand (
    String filename,
    String contentType,
    byte[] bytes
) {} 