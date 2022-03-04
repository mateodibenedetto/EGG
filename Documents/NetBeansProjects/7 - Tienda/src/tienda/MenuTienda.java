
package tienda;

import java.util.Scanner;
import tienda.servicios.ProductoService;
import tienda.servicios.FabricanteService;

public class MenuTienda {
    

    public static void mostrarMenu() throws Exception {
        Scanner r = new Scanner(System.in).useDelimiter("\n");
        FabricanteService fabricanteService = new FabricanteService();
        ProductoService productoService = new ProductoService();
        
        System.out.println("Coloque una letra de la A a la H"
                + "\na) Lista el nombre de todos los productos que hay en la tabla producto."
                + "\nb) Lista los nombres y los precios de todos los productos de la tabla producto."
                + "\nc) Listar aquellos productos que su precio esté entre 120 y 202. "
                + "\nd) Buscar y listar todos los Portátiles de la tabla producto. "
                + "\ne) Listar el nombre y el precio del producto más barato. "
                + "\nf) Ingresar un producto a la base de datos. "
                + "\ng) Ingresar un fabricante a la base de datos. "
                + "\nh) Editar un producto con datos a elección."
        );
        
        
        String opcion = r.next().toLowerCase();
        
        switch (opcion) {
            case "a":
                productoService.imprimirNombres();
            break;   
            case "b":
                productoService.imprimirNombresPrecios();
            break;  
            case "c":
                productoService.imprimirPrecios();
            break;  
            case "d":
                productoService.imprimirPortatiles();
            break;  
            case "e":
                productoService.imprimirMasBarato();
            break;  
            case "f":
                productoService.crearProducto();
            break;  
            case "g":
                fabricanteService.crearFabricante();
            break;  
            case "h":
                System.out.println("Que desea modificar i=Codigo - j=Nombre - k=Precio - l=Salir: ");
                String opcion2 = r.next().toLowerCase();

                switch (opcion2) {
                    case "i":
                        productoService.modificarCodigo();
                    break;
                    case "j":
                        productoService.modificarNombre();
                        productoService.imprimirNombres();
                    break;
                    case "k":
                        productoService.modificarPrecio();
                        productoService.imprimirPrecios();
                    break;
                    default:
                        opcion2 = "l";
                    break;
                }

            break;  
            default:
                System.out.println("No ingreso una opcion valida");
        } 
    }
}
