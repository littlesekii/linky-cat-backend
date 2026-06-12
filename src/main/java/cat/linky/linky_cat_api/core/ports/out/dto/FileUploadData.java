package cat.linky.linky_cat_api.core.ports.out.dto;

public record FileUploadData(
    String filename,
    String contentType,
    byte[] bytes
) {}
