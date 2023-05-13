package med.voll.api.controller;

import org.springframework.web.bind.annotation.*;

// para que se pueda enviar y recibir peticiones por medio del get o post
@RestController
//Mapea la direccion en que se va a encontrar en el servidor o sea en el navegador
@RequestMapping("/hello")
public class helloController {

    //Se ejecuta cuando se hace la peticion get en el navegador cuando carga
    @GetMapping
    public String hello() {
    return "hello Word!";
    }



}

