package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkCreateRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkReorderRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkResponse;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkUpdateRequest;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkCreateUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkDeleteUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkFindAllUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkReorderUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkUpdateUseCase;


@RestController
@RequestMapping("/api/links")
public class LinkController {
    
    private final LinkFindAllUseCase linkFindAllUseCase;
    private final LinkCreateUseCase linkCreateUseCase;
    private final LinkUpdateUseCase linkUpdateUseCase;
    private final LinkReorderUseCase linkReorderUseCase;
    private final LinkDeleteUseCase linkDeleteUseCase;

    public LinkController(
        LinkFindAllUseCase linkFindAllUseCase,
        LinkCreateUseCase linkCreateUseCase,
        LinkUpdateUseCase linkUpdateUseCase, 
        LinkReorderUseCase linkReorderUseCase,
        LinkDeleteUseCase linkDeleteUseCase
    ) {
        this.linkFindAllUseCase = linkFindAllUseCase;
        this.linkCreateUseCase = linkCreateUseCase;
        this.linkUpdateUseCase = linkUpdateUseCase;
        this.linkReorderUseCase = linkReorderUseCase;
        this.linkDeleteUseCase = linkDeleteUseCase;
    }

    @GetMapping
    public ResponseEntity<List<LinkResponse>> findAll(@AuthenticationPrincipal String userId) {
        List<LinkResponse> res = linkFindAllUseCase.execute(UUID.fromString(userId)).stream()
            .map(LinkResponse::fromResult)
            .toList();
        return ResponseEntity.ok().body(res);
    }
    

    @PostMapping
    public ResponseEntity<LinkResponse> create(
        @AuthenticationPrincipal String userId, 
        @RequestBody LinkCreateRequest req
    ) {
        LinkResponse res = LinkResponse.fromResult(
            linkCreateUseCase.execute(req.toCommand(), UUID.fromString(userId))
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(location).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LinkResponse> update(
        @AuthenticationPrincipal String userId, 
        @PathVariable UUID id, 
        @RequestBody LinkUpdateRequest req
    ) {
        LinkResponse res = LinkResponse.fromResult(
            linkUpdateUseCase.execute(id, req.toCommand(), UUID.fromString(userId))
        );
        return ResponseEntity.ok().body(res);
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorder(
        @AuthenticationPrincipal String userId,
        @RequestBody List<LinkReorderRequest> req
    ) {
        linkReorderUseCase.execute(
            req.stream()
                .map(LinkReorderRequest::toCommand)
                .toList(), 
            UUID.fromString(userId)
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @AuthenticationPrincipal String userId, 
        @PathVariable UUID id
    ) {
        linkDeleteUseCase.execute(id, UUID.fromString(userId));
        return ResponseEntity.noContent().build();
    }
}
