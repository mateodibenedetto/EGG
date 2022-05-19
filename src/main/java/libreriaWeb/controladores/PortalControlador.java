
package libreriaWeb.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("")
    public String index() {
        return "index.html";
    }
    
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//    
//    @GetMapping("/logout")
//    public String logout() {
//        return "logout";
//    }
     
    
    // <===== AUTOR ======> //
//    @GetMapping("/listadoAutor")
//    public String administracionAutor() {
//        return "listadoAutores";
//    }
//        
//    @GetMapping("/registroAutor")
//    public String registroAutor() {
//        return "registroAutor";
//    }
//    
    // <===== EDITORIAL ======> //    
    @GetMapping("/listadoEditorial")
    public String administracionEditorial() {
        return "listadoEditorial";
    }
    
    @GetMapping("/registroEditorial")
    public String registroEditorial() {
        return "registroEditorial";
    }

    // <===== LIBRO ======> //
    @GetMapping("/registroLibro")
    public String registroLibro() {
        return "registroLibro";
    }
    
    @GetMapping("/listadoLibro")
    public String administracionLibro() {
        return "listadoLibro";
    }
    
    
    @GetMapping("/mensaje")
    public String mensaje() {
        return "exito.html";
    }

    
}
