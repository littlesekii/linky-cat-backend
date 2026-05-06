package cat.linky.linky_cat_api.adapters.in.web.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.dashboard.DashboardLinksResponse;
import cat.linky.linky_cat_api.core.ports.in.usecase.dashboard.DashboardLinksFetchUseCase;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    
    private final DashboardLinksFetchUseCase dashboardLinksFetchUseCase;

    public DashboardController(DashboardLinksFetchUseCase dashboardLinksFetchUseCase) {
        this.dashboardLinksFetchUseCase = dashboardLinksFetchUseCase;
    }

    @GetMapping("/links")
    public ResponseEntity<DashboardLinksResponse> dashboardLinks(@AuthenticationPrincipal String userId) {
        DashboardLinksResponse res = DashboardLinksResponse.fromResult(
            dashboardLinksFetchUseCase.execute(UUID.fromString(userId))
        );

        return ResponseEntity.ok().body(res);
    }
    
}
