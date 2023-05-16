package med.voll.api.medicocontroller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/medicos")
public class medicos {

    public final Medicorepository medicorepository;

    public medicos(Medicorepository medicorepository) {
        this.medicorepository = medicorepository;
    }


    //RequestBody recibe lo enviado para manipularlo
    @PostMapping
    public void request(@RequestBody @Valid DatosReguistroMedico datosReguistroMedico) {
        medicorepository.save(new Medicos(datosReguistroMedico));
    }
//al momento de agregar el sql debemos agregar las direcciones usuario y pass de la bd en el application.properties en resources

    //getmappin es la solicitud que hace la pagina
    //en donde el finndall() trae todo de la base de datos
    @GetMapping
public Page<DatosListadoMedicos> listarmedicos(@PageableDefault() Pageable pagina){
        return medicorepository.findAll(pagina).map(DatosListadoMedicos::new);
        //para eleiuir cuantos datos van por pagina eso se hace desde el navegador con
        // ?size= tamañó deseado & page= Numero de pag que quiero ver
        // tambien se escriben en pageabledefault pero tieme prioridad si se envia argumentos desde el navegador
}


//Transactional para que se haga el commit cuando finalice correctamente el metodo
@PutMapping
@Transactional
    public void modificarmedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
Medicos medicos = medicorepository.getReferenceById(datosActualizarMedico.id());
        medicos.actualizardatos(datosActualizarMedico);
}
}
