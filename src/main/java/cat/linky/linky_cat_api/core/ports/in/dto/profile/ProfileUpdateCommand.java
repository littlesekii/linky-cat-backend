package cat.linky.linky_cat_api.core.ports.in.dto.profile;

public record ProfileUpdateCommand(
    String displayName,
    String bio
) {} 