
package libreriaWeb.controladores;

import libreriaWeb.errores.ErrorServicio;
import libreriaWeb.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicio eS;
    
    @GetMapping("/registroEditorial")
    public String registroEditorial() {
        return "registroEditorial";
    }
        
    @PostMapping("/registroEditorial")
    public String registrarEditorial(ModelMap modelo, @RequestParam String nombre) throws ErrorServicio {
        try {
            eS.guardar(nombre);
        } catch(ErrorServicio e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre",nombre);
            return "registroEditorial";
        }

        modelo.put("titulo", "Se ha registrado la Editorial correctamente");
        return "exito";
    }
    
    @GetMapping("/listadoEditorial")
    public String litarAutores(Model modelo) {
        modelo.addAttribute("editorial", eS.mostrarTodas());
        
        return "listadoEditorial";
    }
    
    @GetMapping("/modificarEditorial")
    public String modificarAutor() {
        return "modificar.html";
    }

    @PostMapping("/modificarEditorial")
    public String modificarEditorial(@RequestParam String id, @RequestParam String nombre, @RequestParam String apellido) throws ErrorServicio {
        eS.modificar(id, nombre, apellido);
        return "modificar.html";
    }
    
    
    @GetMapping("/eliminarEditorial")
    public String eliminarEditorial() {
        return "eliminar.html";
    }

    @PostMapping("/eliminarAutor")
    public String eliminarAutor(@RequestParam String id) throws ErrorServicio {
        eS.deshabilitar(id);
        return "eliminar.html";
    }
    
    @GetMapping("/mensaje")
    public String mensaje() {
        return "exito.html";
    }
}
