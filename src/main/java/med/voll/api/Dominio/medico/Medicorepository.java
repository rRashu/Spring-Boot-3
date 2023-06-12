package med.voll.api.Dominio.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Medicorepository extends JpaRepository<Medicos,Long> {
    Page<Medicos> findByActivoTrue(Pageable pagina);
}
