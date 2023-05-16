package med.voll.api.medico;

public record DatosListadoMedicos(Long id, String nombre, String especialidad, String documento, String email) {
    public DatosListadoMedicos(Medicos medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono());
    }
}
