package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkCreateRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkResponse;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkUpdateRequest;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkCreateUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkDeleteUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkUpdateUseCase;

@RestController
@RequestMapping("/api/links")
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
    public ResponseEntity<LinkResponse> create(@RequestHeader("X-User-Id") UUID userId, @RequestBody LinkCreateRequest req) {
        LinkResponse res = LinkResponse.fromResult(
            linkCreateUseCase.execute(req.toCommand(), userId)
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(location).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LinkResponse> update(@PathVariable UUID id, @RequestBody LinkUpdateRequest req) {
        LinkResponse res = LinkResponse.fromResult(
            linkUpdateUseCase.execute(id, req.toCommand())
        );
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LinkResponse> delete(@PathVariable UUID id) {
        linkDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
