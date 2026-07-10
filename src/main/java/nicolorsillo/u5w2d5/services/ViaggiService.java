package nicolorsillo.u5w2d5.services;

import lombok.extern.slf4j.Slf4j;
import nicolorsillo.u5w2d5.repositories.ViaggiRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ViaggiService {
    private final ViaggiRepository viaggiRepository;

    public ViaggiService(ViaggiRepository viaggiRepository) {
        this.viaggiRepository = viaggiRepository;
    }
}
