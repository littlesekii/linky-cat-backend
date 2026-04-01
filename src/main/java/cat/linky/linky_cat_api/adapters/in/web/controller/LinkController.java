package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkCreateRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkResponse;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkUpdateRequest;
import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkCreateUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkDeleteUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkUpdateUseCase;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/users/{userId}/links")
public class LinkController {
    
    private final LinkCreateUseCase linkCreateUseCase;
    private final LinkUpdateUseCase linkUpdateUseCase;
    private final LinkDeleteUseCase linkDeleteUseCase;

    public LinkController(
        LinkCreateUseCase linkCreateUseCase, 
        LinkUpdateUseCase linkUpdateUseCase, 
        LinkDeleteUseCase linkDeleteUseCase
    ) {
        this.linkCreateUseCase = linkCreateUseCase;
        this.linkUpdateUseCase = linkUpdateUseCase;
        this.linkDeleteUseCase = linkDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LinkResponse> create(@PathVariable UUID userId, @RequestBody LinkCreateRequest req) {
        User user = linkCreateUseCase.execute(userId, req.toDomain());
        LinkResponse res = LinkResponse.fromDomain(user.getLinks().getLast(), userId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(location).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkResponse> update(@PathVariable UUID userId, @PathVariable UUID id, @RequestBody LinkUpdateRequest req) {
        User user = linkUpdateUseCase.execute(userId, id, req.toDomain());
        LinkResponse res = LinkResponse.fromDomain(user.getLinks().getLast(), userId);
        
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LinkResponse> update(@PathVariable UUID userId, @PathVariable UUID id) {
        linkDeleteUseCase.execute(userId, id);
        
        return ResponseEntity.noContent().build();
    }
}
