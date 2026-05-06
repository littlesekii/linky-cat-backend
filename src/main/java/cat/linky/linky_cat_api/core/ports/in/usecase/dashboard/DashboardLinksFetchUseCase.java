package cat.linky.linky_cat_api.core.ports.in.usecase.dashboard;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.dashboard.DashboardLinksResult;

public interface DashboardLinksFetchUseCase {
    public DashboardLinksResult execute(UUID userId);
}
