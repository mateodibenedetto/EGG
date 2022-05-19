
package libreriaWeb.repositorios;

import java.util.List;
import java.util.Optional;
import libreriaWeb.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public List<Autor> buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT a FROM Autor a WHERE a.id = :id")
    public Optional<Autor> buscarAutoresPorID(@Param("id") String id);
    
    @Query("SELECT a FROM Autor a")
    public List<Autor> buscarAutores();
    
    public List<Autor> findAllById(String id);
  
    
}
