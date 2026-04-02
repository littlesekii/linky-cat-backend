package cat.linky.linky_cat_api.adapters.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile.ProfileResponse;
import cat.linky.linky_cat_api.core.ports.in.usecase.profile.ProfileFetchByUsernameUseCase;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileFetchByUsernameUseCase profileFetchByUsernameUseCase;
    
    public ProfileController(ProfileFetchByUsernameUseCase profileFetchByUsernameUseCase) {
        this.profileFetchByUsernameUseCase = profileFetchByUsernameUseCase;
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> findByUsername(@PathVariable String username) {
        ProfileResponse res = ProfileResponse.fromResult(
            profileFetchByUsernameUseCase.execute(username)
        );
        return ResponseEntity.ok().body(res);
    }
    
}
