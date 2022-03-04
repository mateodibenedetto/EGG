
package tienda.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;

public final class FabricanteDAO extends DAO {
    
    public void guardarFabricante(Fabricante fabricante) throws Exception {
        
        try {
            if (fabricante == null) {
                throw new Exception("Debes indicar un fabricante");
            }
            
            String sql = "INSERT INTO fabricante (codigo, nombre)"
                    + "VALUES ( '" + fabricante.getCodigo() + "' , '" + fabricante.getNombre() + "' );";
            
            insertUpdateDelete(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDB(); 
        }
    }    
    
    public void modificarFabricante(Fabricante fabricante) throws Exception {
        
        try {
            if (fabricante == null) {
                throw new Exception("Debes indicar un usuario que desea modificar");
            }
            
            String sql = "UPDATE fabricante SET "
                    + "codigo = '" + fabricante.getCodigo() + "' WHERE nombre = '" + fabricante.getNombre() + "'";
            
            insertUpdateDelete(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDB();
        }
    }
 
    public void eliminarFabricante(int codigo) throws ClassNotFoundException, SQLException, Exception {
        
        try {

            String sql = "DELETE FROM fabricante WHERE codigo = '" + codigo + "'";

            insertUpdateDelete(sql);
            
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }
    }
    
    //***CONSULTAS***//
    
    public Fabricante buscarFabricantePorCodigo(int codigo) throws SQLException, ClassNotFoundException, Exception {
        
            try {
            String sql = "SELECT * FROM fabricante"
                    + " WHERE codigo = '" + codigo + "'";
            
            consultarDB(sql);
            
            Fabricante fabricante = null;
            
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarDB();
            return fabricante;
            
        } catch(ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw e;
        }
    }
    
    public Fabricante buscarFabricantePorNombre(String nombre) throws SQLException, ClassNotFoundException, Exception {
        
        try {
            String sql = "SELECT * FROM Fabricante"
                    + " WHERE nombre = '" + nombre + "'";
            
            consultarDB(sql);
            
            Fabricante fabricante = null;
            
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarDB();
            return fabricante;
            
        } catch(ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw e;
        }
    }
    
    public Collection<Fabricante> listarFabricantes() throws Exception {
        try {
            String sql = "SELECT codigo, nombre FROM Fabricante";

            consultarDB(sql);

            Fabricante fabricante = null;
            Collection<Fabricante> fabricantes = new ArrayList();
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                fabricantes.add(fabricante);
            }
            desconectarDB();
            return fabricantes;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
}
