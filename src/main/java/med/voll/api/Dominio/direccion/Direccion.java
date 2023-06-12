package med.voll.api.Dominio.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String complemento;
    private String numero;
    private String distrito;
    private String ciudad;

    public Direccion(DatosDireccion direccion) {
    this.calle = direccion.calle();
    this.complemento= direccion.complemento();
    this.numero = direccion.numero();
    this.distrito = direccion.distrito();
    this.ciudad = direccion.ciudad();
    }

    public Direccion actualizardatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.complemento= direccion.complemento();
        this.numero = direccion.numero();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        return this;
    }


}
