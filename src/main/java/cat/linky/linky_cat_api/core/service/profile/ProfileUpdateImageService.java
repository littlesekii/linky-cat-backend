package cat.linky.linky_cat_api.core.service.profile;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.exception.UnauthorizedOperationException;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileUpdateImageCommand;
import cat.linky.linky_cat_api.core.ports.in.usecase.profile.ProfileUpdateImageUseCase;
import cat.linky.linky_cat_api.core.ports.out.dto.FileUploadData;
import cat.linky.linky_cat_api.core.ports.out.file_upload.FileUploaderPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

public class ProfileUpdateImageService implements ProfileUpdateImageUseCase {

    private final ProfileRepositoryPort repositoryPort;
    private final FileUploaderPort fileUploaderPort;

    public ProfileUpdateImageService(
            ProfileRepositoryPort repositoryPort, 
            FileUploaderPort fileUploaderPort
    ) {
        this.repositoryPort = repositoryPort;
        this.fileUploaderPort = fileUploaderPort;
    }

    @Override
    public ProfileResult execute(UUID id, ProfileUpdateImageCommand command, UUID userId) {
        String filename = id.toString() + ".jpg";
        String contentType = command.contentType();
        byte[] bytes = command.bytes();

        Profile existingProfile = repositoryPort.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("service.profile.not_found"));

        if (!existingProfile.getUserId().equals(userId))
            throw new UnauthorizedOperationException("authorization.unauthorized_operation");

        filename = fileUploaderPort.upload(
            new FileUploadData(filename, contentType, bytes)
        );
        existingProfile.updateImageUrl(filename);

        existingProfile = repositoryPort.save(existingProfile);

        ProfileResult result = new ProfileResult(
            null, 
            null,
            null,
            existingProfile.getImageUrl(),
            null, 
            null
        );

        return result;
    }
    
}
