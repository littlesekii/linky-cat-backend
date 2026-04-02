package cat.linky.linky_cat_api.core.ports.in.usecase.link;

import java.util.UUID;

public interface LinkDeleteUseCase {
    public void execute(UUID id);
}
