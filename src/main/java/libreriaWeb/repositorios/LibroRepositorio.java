
package libreriaWeb.repositorios;

import java.util.List;
import java.util.Optional;
import libreriaWeb.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
    @Query("SELECT l FROM Libro l")
    public List<Libro> buscarLibros();
    
    @Query("SELECT l FROM Libro l WHERE l.id = :param")
    public Optional<Libro> buscarLibrosPorID(@Param("param") String id);
   
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public List<Libro> buscarPorTitulo(@Param("titulo") String titulo);
     
//    @Query("SELECT l, a FROM Libro l, Autor a WHERE l.autor_id = :a.id")
//    public Optional<Libro> findId(@Param("id") String id);
//    
    public List<Libro> findAllById(String id);
    
//    @Query("SELECT l FROM Libro l WHERE l.autor_id LIKE :id")
//    public Optional<Libro> buscarPorIdAutor(@Param("id") String id);
    
}
