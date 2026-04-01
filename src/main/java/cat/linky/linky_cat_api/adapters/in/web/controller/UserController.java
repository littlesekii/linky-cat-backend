package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.user.UserCreateRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.user.UserPartialUpdateRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.user.UserResponse;
import cat.linky.linky_cat_api.core.ports.in.dto.UserPartialUpdateDTO;
import cat.linky.linky_cat_api.core.ports.in.usecase.user.UserCreateUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.user.UserFindByIdUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.user.UserPartialUpdateUseCase;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserFindByIdUseCase userFindByIdUseCase;
    private final UserCreateUseCase userCreateUseCase;
    private final UserPartialUpdateUseCase userPartialUpdateUseCase;

    public UserController(
        UserFindByIdUseCase userFindByIdUseCase, 
        UserCreateUseCase userCreateUseCase, 
        UserPartialUpdateUseCase userPartialUpdateUseCase
    ) {
        this.userFindByIdUseCase = userFindByIdUseCase;
        this.userCreateUseCase = userCreateUseCase;
        this.userPartialUpdateUseCase = userPartialUpdateUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        UserResponse res = UserResponse.fromDomain(
            userFindByIdUseCase.execute(id)
        );
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest req) {
        UserResponse res = UserResponse.fromDomain(
            userCreateUseCase.execute(req.toDomain())
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(location).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> partialUpdate(@PathVariable UUID id, @RequestBody UserPartialUpdateRequest req) {
        UserResponse res = UserResponse.fromDomain(
            userPartialUpdateUseCase.execute(id, new UserPartialUpdateDTO(req.displayName(), req.bio()))
        );
        return ResponseEntity.ok().body(res);
    }

    
}
