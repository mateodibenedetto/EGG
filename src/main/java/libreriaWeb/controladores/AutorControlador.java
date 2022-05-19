
package libreriaWeb.controladores;

import libreriaWeb.entidades.Autor;
import libreriaWeb.errores.ErrorServicio;
import libreriaWeb.repositorios.AutorRepositorio;
import libreriaWeb.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
  
    
    @Autowired
    private AutorServicio aS;
    
    @Autowired
    private AutorRepositorio aR;
    
    @GetMapping("/registroAutor")
    public String registroAutor() {
        return "registroAutor";
    }
        
    @PostMapping("/registroAutor")
    public String registrarAutor(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido) throws ErrorServicio {
        try {
            aS.registrar(nombre, apellido);
        } catch(ErrorServicio e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre",nombre);
            modelo.put("apellido",apellido);
            return "registroAutor";
        }

        modelo.put("titulo", "Bienvenido a LibreiWeb");
        modelo.put("descripcion", "Se ha registrado el autor correctamente");
        return "exito";
    }
    
    @GetMapping("/listadoAutor")
    public String litarAutores(Model modelo) {
        modelo.addAttribute("autor", aS.mostrarTodos());
        
        return "listadoAutores";
    }
    
    @GetMapping("/modificarAutor/{id}")
    public String modificar_Autor(ModelMap modelo, @PathVariable String id, String nombre, String apellido) throws ErrorServicio {
        Autor autor = aS.buscarPorId(id);
        
        modelo.put("autor", autor);
        
        return "actualizarAutor";
    }

    @PostMapping("/modificarAutor/{id}")
    public String modificarAutor(ModelMap modelo, @PathVariable String id, String nombre, String apellido) throws ErrorServicio {
       
        try {
            aS.modificar(id, nombre, apellido);
        } catch(ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "actualizarAutor";
        }
        
        modelo.put("titulo", "Se ha actualizao el autor correctamente");
        return "exito";
        
    }
    
    @GetMapping("/darBaja/{id}")
//    @RequestMapping(method=RequestMethod.GET,value="/darBaja/{id}")
    public String bajaAutor(ModelMap modelo, @PathVariable() String id) throws ErrorServicio {
        try {
            modelo.addAttribute("autor", aR.findAllById(id));
            this.aS.deshabilitar(id);
        } catch(ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "litadoAutores";
        }

        return "redirect:/autor/listadoAutor";
    }
    
    @GetMapping("/darAlta/{id}")
    public String altaAutor(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
        try {
            modelo.addAttribute("autor", aR.findAllById(id));
            this.aS.habilitar(id);
        } catch(ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "listadoAutores";
        }

        return "redirect:/autor/listadoAutor";
    }
    
    @GetMapping("/eliminarAutor/{id}")
    public String eliminarAutor(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
        modelo.addAttribute("autor", aR.findAllById(id));
        this.aS.eliminar(id);

        return "redirect:/autor/listadoAutor";
    }
    

}
