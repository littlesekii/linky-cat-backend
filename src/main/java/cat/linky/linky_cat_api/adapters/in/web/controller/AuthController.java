package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth.AuthLoginRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth.AuthLoginResponse;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth.AuthRegisterRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth.AuthRegisterResponse;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthCheckEmailUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthCheckUsernameUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthLoginUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthRegisterUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthRegisterUseCase authRegisterUseCase;
    private final AuthLoginUseCase authLoginUseCase;

    private final AuthCheckEmailUseCase authCheckEmailUseCase;
    private final AuthCheckUsernameUseCase authCheckUsernameUseCase;
    
    public AuthController(
        AuthRegisterUseCase authRegisterUseCase,
        AuthLoginUseCase authLoginUseCase, 
        AuthCheckEmailUseCase authCheckEmailUseCase, 
        AuthCheckUsernameUseCase authCheckUsernameUseCase
    ) {
        this.authRegisterUseCase = authRegisterUseCase;
        this.authLoginUseCase = authLoginUseCase;
        this.authCheckEmailUseCase = authCheckEmailUseCase;
        this.authCheckUsernameUseCase = authCheckUsernameUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> register(@RequestBody AuthRegisterRequest req) {   
         AuthRegisterResponse res = AuthRegisterResponse.fromResult(
            authRegisterUseCase.execute(req.toCommand())
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.user().id())
            .toUri();

        return ResponseEntity.created(location).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest req) {   
         AuthLoginResponse res = AuthLoginResponse.fromResult(
            authLoginUseCase.execute(req.toCommand())
        );

        return ResponseEntity.ok().body(res);
    }
    
    @GetMapping("/check-email")
    public ResponseEntity<Void> checkEmail(@RequestParam String email) {
        authCheckEmailUseCase.execute(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check-username")
    public ResponseEntity<Void> checkUsername(@RequestParam String username) {
        authCheckUsernameUseCase.execute(username);
        return ResponseEntity.ok().build();
    }  
}
