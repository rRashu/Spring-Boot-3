package med.voll.api.medicocontroller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedicos;
import med.voll.api.medico.DatosReguistroMedico;
import med.voll.api.medico.Medicorepository;
import med.voll.api.medico.Medicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicos")
public class medicos {

    @Autowired
    public Medicorepository medicorepository;



    //RequestBody recibe lo enviado para manipularlo
    @PostMapping
    public void request(@RequestBody @Valid DatosReguistroMedico datosReguistroMedico) {
        medicorepository.save(new Medicos(datosReguistroMedico));
    }
//al momento de agregar el sql debemos agregar las direcciones usuario y pass de la bd en el application.properties en resources

    //getmappin es la solicitud que hace la pagina
    //en donde el finndall() trae todo de la base de datos
    @GetMapping
public List<DatosListadoMedicos> listarmedicos(){
        return medicorepository.findAll().stream().map(DatosListadoMedicos::new).toList();
}
}
