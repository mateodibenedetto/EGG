
package tienda.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
    
    protected Connection conexion; // Es una interfaz con todos los métodos para contactar una base de datos
    protected ResultSet resultado; // representa un conjunto de filas recuperadas debido a la ejecución de una consulta
    protected Statement sentencia; // Encapsula una instrucción SQL
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver"; // Es el enlace de comunicaciones de la base de datos
    
    protected void conectarDB() throws Exception {
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos");
        }
    }

    
    protected void desconectarDB() throws SQLException{
        
        try {
            // Si resulset, statment y connection no estan cerradas se van a cerrar
            if (resultado != null) { 
                resultado.close();
            }
            if (sentencia != null) { 
                sentencia.close();
            }
            if (conexion != null) { 
                conexion.close();
            }
            
        } catch(SQLException e) {
            throw e;
        }   
    }
    
    protected void insertUpdateDelete(String sql) throws ClassNotFoundException, SQLException, Exception{
        try {
            
            conectarDB();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
            
        } catch(ClassNotFoundException | SQLException e) {
            // conexion.rollback(); // sirve para omitir todo lo que se habia insertado
            /*
                en Workbench
                SET autocommit = true;
                COMMIT;
            */
            throw e;
        } finally {
            desconectarDB();
        }
    }
    
    protected void consultarDB(String sql) throws ClassNotFoundException, SQLException, Exception {
        try {
            
            conectarDB();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            
        } catch(ClassNotFoundException | SQLException e) {
            throw e;
        }
        
    }
}
