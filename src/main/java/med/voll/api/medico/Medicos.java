package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String documento;
    private String telefono;
    private String email;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medicos(DatosReguistroMedico datosReguistroMedico) {
        this.nombre = datosReguistroMedico.nombre();
        this.documento = datosReguistroMedico.documento();
        this.telefono = datosReguistroMedico.telefono();
        this.email = datosReguistroMedico.email();
        this.especialidad = datosReguistroMedico.especialidad();
        this.direccion = new Direccion(datosReguistroMedico.direccion());
    }

    public void actualizardatos(DatosActualizarMedico datosActualizarMedico){
       if (datosActualizarMedico.nombre() != null){
           this.nombre = datosActualizarMedico.nombre();
       }
       if(datosActualizarMedico.documento()!=null){
           this.nombre = datosActualizarMedico.documento();
       }
       if (datosActualizarMedico.direccion()!= null)
           this.direccion = direccion.actualizardatos(datosActualizarMedico.direccion());
    }

}
