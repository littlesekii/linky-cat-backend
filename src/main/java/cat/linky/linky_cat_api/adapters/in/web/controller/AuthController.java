package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth.AuthRegisterRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth.AuthRegisterResponse;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthRegisterUseCase;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthRegisterUseCase authRegisterUseCase;
    
    public AuthController(AuthRegisterUseCase authRegisterUseCase) {
        this.authRegisterUseCase = authRegisterUseCase;
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
    
}
