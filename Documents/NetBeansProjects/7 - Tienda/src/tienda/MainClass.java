
package tienda;

import java.sql.SQLException;

public class MainClass {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        
        try {
            MenuTienda.mostrarMenu();
        } catch (Exception e) {
            System.out.println(e);
        }   
    }
}
