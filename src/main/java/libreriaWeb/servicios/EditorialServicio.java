
package libreriaWeb.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import libreriaWeb.entidades.Editorial;
import libreriaWeb.repositorios.EditorialRepositorio;
import libreriaWeb.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    
    Scanner r = new Scanner(System.in).useDelimiter("\n");
    
    @Autowired // La variable la inicializa el servidor de aplicaciones
    private EditorialRepositorio editorialRepositorio;
    
    // <===== VALIDAR ======> //
    public void validar(String nombre) throws ErrorServicio {
        if(nombre == null || nombre.trim().isEmpty()) throw new ErrorServicio("El nombre del Editorial no puede estar vacío");
        
        if(nombre.length() < 3) throw new ErrorServicio("El nombre de la Editorial no puede ser tan corto");
    }
    
    // <===== GUARDAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void guardar(String nombre) throws ErrorServicio {
        
        validar(nombre);
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        
        editorialRepositorio.save(editorial); // El repositorio lo almacena en la base de datos
    }
    
    // <===== MODIFICAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre, String alta) throws ErrorServicio {
        
        validar(nombre);
       
        Optional<Editorial> respuesta = editorialRepositorio.findById(id); // El Optional verifica si existe un autor segun el id
        if(respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
        
            if(alta.equalsIgnoreCase("alta")) {
                editorial.setAlta(Boolean.TRUE);
            } else if(alta.equalsIgnoreCase("baja")) {
                editorial.setAlta(Boolean.TRUE);
            } else {
                throw new ErrorServicio("Valor no valido");
            } 
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("No se encontro la Editorial solicitada");
        }
       
    }
    
    // <===== DESHABILITAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void deshabilitar(String id) throws ErrorServicio {
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id); // El Optional verifica si existe un autor segun el id
        if(respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(Boolean.FALSE);
            
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("No se encontro la Editorial solicitado");
        }
    }
    
    // <===== HABILITAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void habilitar(String id) throws ErrorServicio {
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id); // El Optional verifica si existe un autor segun el id
        if(respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(Boolean.TRUE);
            
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("No se encontro la Editorial solicitado");
        }
    }
    
        // <===== MOSTRAR ======> //
    @Transactional()
    public List<Editorial> mostrarTodas() {
        return editorialRepositorio.findAll();
    }
    
    // <===== ELIMINAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<Editorial> optional = editorialRepositorio.findById(id);
        if(optional.isPresent()) editorialRepositorio.delete(optional.get());
    }
}
