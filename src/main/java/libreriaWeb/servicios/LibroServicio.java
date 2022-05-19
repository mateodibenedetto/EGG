
package libreriaWeb.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import libreriaWeb.entidades.Autor;
import libreriaWeb.entidades.Editorial;
import libreriaWeb.entidades.Libro;
import libreriaWeb.errores.ErrorServicio;
import libreriaWeb.repositorios.AutorRepositorio;
import libreriaWeb.repositorios.EditorialRepositorio;
import libreriaWeb.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {
    Scanner r = new Scanner(System.in).useDelimiter("\n");
    
    @Autowired // La variable la inicializa el servidor de aplicaciones
    private LibroRepositorio libroRepositorio;
    @Autowired 
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    public void validar( String isbn, String titulo, Integer anio) throws ErrorServicio {
        String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(isbn);
        if(!matcher.matches()) throw new ErrorServicio("El ISBN debe ser de 10");
        
        if(titulo == null || titulo.trim().isEmpty()) throw new ErrorServicio("El titulo del libro no puede estar vacío");
        
        if(anio < 1200 || anio > 2022) throw new ErrorServicio("Debe ingresar un año valido");
    }
    
    // <===== GUARDAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void guardar(String isbn, String titulo, Integer anio, Integer ejemplares, String idEditorial, String idAutor) throws ErrorServicio {
        
        if (ejemplares == null) throw new ErrorServicio("El numero de ejemplares  no puede ser nulo");

        if (idAutor == null) throw new ErrorServicio("El nombre del autor no puede ser nulo");
        
        if (idEditorial == null) throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
        
        
        validar(isbn,titulo,anio);
       
        Optional<Editorial> respuesta = editorialRepositorio.findById(idEditorial);
        if(!respuesta.isPresent()) {
            throw new ErrorServicio("No se encontro ninguna Editorial con el id que ingreso");
        } else {
            Optional<Autor> respuesta2 = autorRepositorio.findById(idAutor);
            if(!respuesta2.isPresent()) {
                throw new ErrorServicio("No se encontro ningun Editorial con el id que ingreso");
            } else {
                Editorial editorial = respuesta.get();
                Autor autor = respuesta2.get();
                
                Libro libro = new Libro();
                libro.setIsbn(isbn);
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setAlta(Boolean.TRUE);

                libroRepositorio.save(libro); // El repositorio lo almacena en la base de datos
            }
        }
    }
    
    // <===== MODIFICAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String isbn, String titulo, Integer anio, Integer ejemplares, String idEditorial, String idAutor, String alta) throws ErrorServicio {
        
        validar(isbn, titulo, anio);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(idEditorial);
        if(!respuesta.isPresent()) {
            throw new ErrorServicio("No se encontro ninguna Editorial con el id que ingreso");
        } else {
            Optional<Autor> respuesta2 = autorRepositorio.findById(idAutor);
            if(!respuesta.isPresent()) {
                throw new ErrorServicio("No se encontro ningun Libro con el id que ingreso");
            } else {
                Editorial editorial = respuesta.get();
                Autor autor = respuesta2.get();
         
                Optional<Libro> respuesta3 = libroRepositorio.findById(isbn); 
                if(respuesta.isPresent()) {
                    Libro libro = respuesta3.get();
                    libro.setTitulo(titulo);
                    libro.setAnio(anio);
                    libro.setEjemplares(ejemplares);
                    libro.setAutor(autor);
                    libro.setEditorial(editorial);
                    
                    if(alta.equalsIgnoreCase("alta")) {
                        libro.setAlta(Boolean.TRUE);
                    } else if(alta.equalsIgnoreCase("baja")) {
                        libro.setAlta(Boolean.TRUE);
                    } else {
                        throw new ErrorServicio("Valor no valido");
                    } 
                    libroRepositorio.save(libro);
                } else {
                    throw new ErrorServicio("No se encontro el Libro solicitado");
                }
            }
        }
    }
    
    
    // <===== DESHABILITAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void deshabilitar(String id) throws ErrorServicio {
        
        Optional<Libro> respuesta = libroRepositorio.findById(id); 
        if(respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(Boolean.FALSE);
            
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("No se encontro el Libro solicitado");
        }
    }
    
    // <===== HABILITAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void habilitar(String id) throws ErrorServicio {
        
        Optional<Libro> respuesta = libroRepositorio.findById(id); 
        if(respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(Boolean.TRUE);
            
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("No se encontro el Libro solicitado");
        }
    }
    
    // <===== MOSTRAR ======> //
    @Transactional(readOnly = true)
    public List<Libro> mostrarTodos() {
        return libroRepositorio.findAll();
    }
    
    // <===== ELIMINAR ======> //
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<Libro> optional = libroRepositorio.buscarLibrosPorID(id);
        
        if(optional.isPresent()) libroRepositorio.delete(optional.get());
    }
}
