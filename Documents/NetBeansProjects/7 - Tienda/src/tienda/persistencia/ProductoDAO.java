
package tienda.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;


public final class ProductoDAO extends DAO {
    
    public void guardarProducto(Producto producto) throws Exception {
        
        try {
            if (producto == null) {
                throw new Exception("Debes indicar un producto");
            }
            
            String sql = "INSERT INTO producto (nombre, precio, codigo_fabricante)"
                    + "VALUES ( '" + producto.getNombre() + "' , "
                    + "'" + producto.getPrecio()+ "', '" + producto.getCodigo_fabricante() +"' );";
            
            insertUpdateDelete(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDB(); 
        }
    }    
    
    public void modificarNombreProducto(Producto producto) throws Exception {
        
        try {
            if (producto == null) {
                throw new Exception("Debes indicar el producto que desea modificar");
            }
            
            String sql = "UPDATE producto SET "
                    + "nombre = '" + producto.getNombre() + "' WHERE codigo = '" + producto.getCodigo() + "';";
            
            insertUpdateDelete(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDB();
        }
    }
    
    public void modificarPrecio(Producto producto) throws Exception {
        
        try {
            if (producto == null) {
                throw new Exception("Debes indicar el producto que desea modificar");
            }
            
            String sql = "UPDATE producto SET "
                    + "precio = '" + producto.getPrecio() + "' WHERE codigo = '" + producto.getCodigo() + "';";
            
            insertUpdateDelete(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDB();
        }
    }
    
 
    public void eliminarProducto(int codigo) throws ClassNotFoundException, SQLException, Exception {
        
        try {

            String sql = "DELETE FROM producto WHERE codigo = '" + codigo + "';";

            insertUpdateDelete(sql);
            
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }
    }
    
    //***CONSULTAS***//
    
    public Producto buscarProductoPorCodigo(int codigo) throws SQLException, ClassNotFoundException, Exception {
        
            try {
            String sql = "SELECT * FROM producto"
                    + " WHERE codigo = '" + codigo + "';";
            
            consultarDB(sql);
            
            Producto producto = null;
            
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
            }
            desconectarDB();
            return producto;
            
        } catch(ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw e;
        }
    }
    
    public Producto buscarProductoPorNombre(String nombre) throws SQLException, ClassNotFoundException, Exception {
        
        try {
            String sql = "SELECT * FROM producto"
                    + " WHERE nombre = '" + nombre + "';";
            
            consultarDB(sql);
            
            Producto producto = null;
            
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
            }
            desconectarDB();
            return producto;
            
        } catch(ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw e;
        }
    }
    
    public Producto buscarProductoPorPrecio(double precio) throws SQLException, ClassNotFoundException, Exception {
        
        try {
            String sql = "SELECT * FROM producto"
                    + " WHERE precio = '" + precio + "';";
            
            consultarDB(sql);
            
            Producto producto = null;
            
            while (resultado.next()) {
                producto = new Producto();
                producto.setPrecio(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
            }
            desconectarDB();
            return producto;
            
        } catch(ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw e;
        }
    }
    
    public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT codigo, nombre, precio, codigo_fabricante FROM producto;";

            consultarDB(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getInt(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarDB();
            return productos;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
    
    public Collection<Producto> listarNombreProductos() throws Exception {
        try {
            String sql = "SELECT nombre FROM producto;";

            consultarDB(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                productos.add(producto);
            }
            desconectarDB();
            return productos;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
    
    public Collection<Producto> listarNombrePrecioProductos() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto;";

            consultarDB(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getInt(2));
                productos.add(producto);
            }
            desconectarDB();
            return productos;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
    
    public Collection<Producto> listarPrecios() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto WHERE precio >= 120 AND precio <= 202;";

            consultarDB(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getInt(2));
                productos.add(producto);
            }
            desconectarDB();
            return productos;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
    
        public Collection<Producto> listarPortatiles() throws Exception {
        try {
            String sql = "SELECT nombre FROM producto WHERE nombre LIKE '%Portatil%';";

            consultarDB(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                productos.add(producto);
            }
            desconectarDB();
            return productos;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
        
    public Collection<Producto> listarMasBarato() throws Exception {
        try {
            String sql = "SELECT nombre, MIN(precio) FROM producto;";

            consultarDB(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getInt(2));
                productos.add(producto);
            }
            desconectarDB();
            return productos;
        } catch (ClassNotFoundException | SQLException e) {
            desconectarDB();
            throw new Exception("Error de sistema");
        }
    }
}
