
package libreriaWeb.controladores;

import libreriaWeb.errores.ErrorServicio;
import libreriaWeb.servicios.AutorServicio;
import libreriaWeb.servicios.EditorialServicio;
import libreriaWeb.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio lS;
    
    @Autowired
    private EditorialServicio eS;
        
    @Autowired
    private AutorServicio aS;
    
    @GetMapping("/registroLibro")
    public String registroAutor() {
        return "registroLibro";
    }
        
    @PostMapping("/registroLibro")
    public String registrarLibro(ModelMap modelo, @RequestParam String isbn,  @RequestParam String titulo, 
            @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam String idEditorial, 
            @RequestParam String idAutor ) throws ErrorServicio {
        try {
            lS.guardar(isbn, titulo, anio, ejemplares, idEditorial, idAutor);
        } catch(ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "registroLibro";
        }

        modelo.put("titulo", "Se ha registrado el Libro correctamente");
        return "exito";
    }
    
    @GetMapping("/listadoLibro")
    public String litarAutores(Model modelo) {
        modelo.addAttribute("libro", lS.mostrarTodos());
        modelo.addAttribute("editorial", eS.mostrarTodas());
        modelo.addAttribute("autor", aS.mostrarTodos());
        
        return "listadoLibro";
    }
    
    
    @GetMapping("/modificarLibro")
    public String modificarLibro() {
        return "modificar.html";
    }

    @PostMapping("/modificarLibro")
    public String modificarLibro(@RequestParam String isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam String idEditorial, @RequestParam String idAutor, @RequestParam String alta) throws ErrorServicio {
        lS.modificar(isbn, titulo, anio, ejemplares, idEditorial, idAutor, alta);
        return "modificar.html";
    }
//    
//    
//    @GetMapping("/eliminarLibro")
//    public String eliminarLibro() {
//        return "eliminar.html";
//    }
//
//    @PostMapping("/eliminarLibro")
//    public String eliminarLibro(@RequestParam String id) throws ErrorServicio {
//        lS.deshabilitar(id);
//        return "eliminar.html";
//    }
//    
    @GetMapping("/mensaje")
    public String mensaje() {
        return "exito.html";
    }
}
