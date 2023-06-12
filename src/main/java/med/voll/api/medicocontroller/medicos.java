package med.voll.api.medicocontroller;

import jakarta.validation.Valid;
import med.voll.api.Dominio.direccion.DatosDireccion;
import med.voll.api.Dominio.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/medicos")
public class medicos {

    public final Medicorepository medicorepository;

    public medicos(Medicorepository medicorepository) {
        this.medicorepository = medicorepository;
    }


    //RequestBody recibe lo enviado para manipularlo
    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> request(@RequestBody @Valid DatosReguistroMedico datosReguistroMedico, UriComponentsBuilder uriComponentsBuilder) {
        Medicos medicos = medicorepository.save(new Medicos(datosReguistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medicos.getId(), medicos.getNombre(), medicos.getEmail(), medicos.getTelefono(), medicos.getEspecialidad().toString(), new DatosDireccion(medicos.getDireccion().getCalle(), medicos.getDireccion().getDistrito(), medicos.getDireccion().getCiudad(), medicos.getDireccion().getNumero(), medicos.getDireccion().getComplemento()));
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medicos.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaMedico);
    }
//al momento de agregar el sql debemos agregar las direcciones usuario y pass de la bd en el application.properties en resources

    //getmappin es la solicitud que hace la pagina
    //en donde el finndall() trae todo de la base de datos
    @GetMapping
public ResponseEntity<Page<DatosListadoMedicos>> listarmedicos(@PageableDefault() Pageable pagina){
        //busca todos los valores sin filtro
        //return medicorepository.findAll(pagina).map(DatosListadoMedicos::new);

        //retorna solo la busqueda del campo colocado
        return ResponseEntity.ok(medicorepository.findByActivoTrue(pagina).map(DatosListadoMedicos::new));
        //para eleiuir cuantos datos van por pagina eso se hace desde el navegador con
        // ?size= tamañó deseado & page= Numero de pag que quiero ver
        // tambien se escriben en pageabledefault pero tieme prioridad si se envia argumentos desde el navegador
    }


    //Transactional para que se haga el commit cuando finalice correctamente el metodo
    @PutMapping
    @Transactional
    public ResponseEntity modificarmedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medicos medicos = medicorepository.getReferenceById(datosActualizarMedico.id());
        medicos.actualizardatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medicos.getId(), medicos.getNombre(), medicos.getEmail(), medicos.getTelefono(), medicos.getEspecialidad().toString(), new DatosDireccion(medicos.getDireccion().getCalle(), medicos.getDireccion().getDistrito(), medicos.getDireccion().getCiudad(), medicos.getDireccion().getNumero(), medicos.getDireccion().getComplemento())));
    }

    // delete logico coloca solo una referencia para saber si esta activo o no
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarmedico(@PathVariable Long id) {
        Medicos medicos = medicorepository.getReferenceById(id);
        medicos.desactivarmedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> Listarunmedico(@PathVariable Long id) {
        Medicos medicos = medicorepository.getReferenceById(id);
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medicos.getId(), medicos.getNombre(), medicos.getEmail(), medicos.getTelefono(), medicos.getEspecialidad().toString(), new DatosDireccion(medicos.getDireccion().getCalle(), medicos.getDireccion().getDistrito(), medicos.getDireccion().getCiudad(), medicos.getDireccion().getNumero(), medicos.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosRespuestaMedico);
    }


/* Eliminar completamente de la base de datos
public void eliminarmedico(@PathVariable Long id ){
    Medicos medicos = medicorepository.getReferenceById(id);
    medicorepository.delete(medicos);

}*/
}
