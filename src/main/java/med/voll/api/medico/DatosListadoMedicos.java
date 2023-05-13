package med.voll.api.medico;

public record DatosListadoMedicos(String nombre, String especialidad, String documento,String email) {
    public DatosListadoMedicos(Medicos medico) {
        this(medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono());
    }
}
