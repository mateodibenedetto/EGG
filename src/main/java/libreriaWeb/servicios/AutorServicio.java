
package libreriaWeb.servicios;

import java.util.List;
import java.util.Optional;
import libreriaWeb.entidades.Autor;
import libreriaWeb.errores.ErrorServicio;
import libreriaWeb.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
 
//    @Qualifier("autor")
    @Autowired // La variable la inicializa el servidor de aplicaciones
    private AutorRepositorio autorRepositorio;
    
//    @Autowired // La variable la inicializa el servidor de aplicaciones
//    private LibroRepositorio libroRepositorio;
    
    // <===== VALIDAR ======> //
    public void validar(String nombre, String apellido) throws ErrorServicio {
        if(nombre == null || nombre.trim().isEmpty()) throw new ErrorServicio("El nombre del Autor no puede estar vacío");
        
        if(apellido == null || apellido.trim().isEmpty()) throw new ErrorServicio("El apellido del Autor no puede estar vacío");
        
        if(nombre.length() < 3) throw new ErrorServicio("El nombre del Autor no puede ser tan corto");
        
        if(apellido.length() < 4) throw new ErrorServicio("El apellido del Autor no puede ser tan corto");
    }
    
    // <===== BUSCAR ======> //
    public Autor buscarPorNombre(String nombre) throws ErrorServicio {
        
        List<Autor> autores = autorRepositorio.buscarPorNombre(nombre);
        Autor autor = autores.get(0);
        
        return autor; 
    }
    
    public Autor buscarPorId(String id) throws ErrorServicio {
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if(respuesta.isPresent()) {
            Autor autor = respuesta.get();
 
            return autor; 
        } else {
            throw new ErrorServicio("No se encontro el Autor solicitado");
        }
        
    }
    
    
    // <===== REGISTRAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void registrar(String nombre, String apellido) throws ErrorServicio {
        validar(nombre, apellido);
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autor.setAlta(Boolean.TRUE);
        
        autorRepositorio.save(autor); // El repositorio lo almacena en la base de datos
    }
    
    // <===== MODIFICAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre, String apellido) throws ErrorServicio {
        validar(nombre, apellido);
       
        Optional<Autor> respuesta = autorRepositorio.findById(id); // El Optional verifica si existe un autor segun el id
        if(respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autor.setApellido(apellido);

            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontro el Autor solicitado");
        }
       
    }
    
    // <===== DESHABILITAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public boolean deshabilitar(String id) throws ErrorServicio {
        
        Optional<Autor> respuesta = this.autorRepositorio.findById(id); // El Optional verifica si existe un autor segun el id
        if(respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(Boolean.FALSE);
//            autor.setAlta(!autor.getAlta());
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontro el Autor solicitado");
        }
        return false;
    }
    
    // <===== HABILITAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public boolean habilitar(String id) throws ErrorServicio {
        
        Optional<Autor> respuesta = this.autorRepositorio.findById(id); // El Optional verifica si existe un autor segun el id
        if(respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(Boolean.TRUE);
            
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontro el Autor solicitado");
        }
        return true;
    }
        
    // <===== MOSTRAR ======> //
    @Transactional()
    public List<Autor> mostrarTodos() {
        return autorRepositorio.findAll();
    }
    
    // <===== ELIMINAR ======> //    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio {
        Optional<Autor> optional = autorRepositorio.findById(id);
        
        if(optional.isPresent()) {
            autorRepositorio.delete(optional.get());
        }

    }
        
        
}        
    

    
//    @Override
//    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
//        List<Autor> autor = autorRepositorio.buscarPorNombre(nombre);
//        if(autor != null) {
//            
//            List<GrantedAuthority> permisos = new ArrayList<>();
//            
//            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_LIBROS");
//            permisos.add(p1);
//            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_EDITORIAL");
//            permisos.add(p2);
//                    
//            User user = new User(autor.get(0).getNombre(), autor.get(0).getApellido(), permisos);
//            return user;
//        } else {
//            return null;
//        }
//    }

