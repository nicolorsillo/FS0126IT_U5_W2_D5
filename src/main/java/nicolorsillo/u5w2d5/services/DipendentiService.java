package nicolorsillo.u5w2d5.services;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import nicolorsillo.u5w2d5.repositories.DipendentiRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DipendentiService {
    private final DipendentiRepository dipendentiRepository;
    private final Cloudinary cloudinary;

    public DipendentiService(DipendentiRepository dipendentiRepository, Cloudinary cloudinary) {
        this.dipendentiRepository = dipendentiRepository;
        this.cloudinary = cloudinary;
    }
}
