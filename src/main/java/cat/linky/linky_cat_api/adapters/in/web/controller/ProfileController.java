package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile.ProfileResponse;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile.ProfileUpdateRequest;
import cat.linky.linky_cat_api.core.ports.in.usecase.profile.ProfileFetchByUsernameUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.profile.ProfileUpdateUseCase;


@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileFetchByUsernameUseCase profileFetchByUsernameUseCase;
    private final ProfileUpdateUseCase profileUpdateUseCase;
    
    public ProfileController(
        ProfileFetchByUsernameUseCase profileFetchByUsernameUseCase, 
        ProfileUpdateUseCase profileUpdateUseCase
    ) {
        this.profileFetchByUsernameUseCase = profileFetchByUsernameUseCase;
        this.profileUpdateUseCase = profileUpdateUseCase;
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> findByUsername(@PathVariable String username) {
        ProfileResponse res = ProfileResponse.fromResult(
            profileFetchByUsernameUseCase.execute(username)
        );
        return ResponseEntity.ok().body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfileResponse> update(@AuthenticationPrincipal String userId, @PathVariable UUID id, @RequestBody ProfileUpdateRequest req) {
         ProfileResponse res = ProfileResponse.fromResult(
            profileUpdateUseCase.execute(id, req.toCommand(), UUID.fromString(userId))
         );

        return ResponseEntity.ok().body(res);
    }
    
}
