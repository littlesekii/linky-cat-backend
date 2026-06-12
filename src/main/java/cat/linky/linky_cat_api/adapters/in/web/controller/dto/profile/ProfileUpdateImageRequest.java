package cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileUpdateImageCommand;

public record ProfileUpdateImageRequest(
    MultipartFile image
) {
    public ProfileUpdateImageCommand toCommand() {
        try {
            return new ProfileUpdateImageCommand(
                image.getOriginalFilename(), 
                image.getContentType(), 
                image.getBytes()
            );
        } catch (IOException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }
}
