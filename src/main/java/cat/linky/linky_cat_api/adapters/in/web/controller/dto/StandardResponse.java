package cat.linky.linky_cat_api.adapters.in.web.controller.dto;

public record StandardResponse(
    String message
) {
    public static StandardResponse success() {
        return new StandardResponse("success");
    }
} 
