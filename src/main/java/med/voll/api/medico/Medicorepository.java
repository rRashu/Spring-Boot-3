package med.voll.api.medico;

import med.voll.api.medicocontroller.medicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Medicorepository extends JpaRepository<Medicos,Long> {
}
