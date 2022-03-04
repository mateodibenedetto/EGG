
package tienda.servicios;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;


public class ProductoService {
    private final ProductoDAO dao;

    public ProductoService() {
        this.dao = new ProductoDAO();
    }
    
    
    public void crearProducto() throws Exception {

        Scanner r = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = r.next();
        System.out.println("Ingrese el precio del producto: ");
        int precio = r.nextInt();
        System.out.println("Ingrese el codigo del fabricante");
        int codigoFabricante = r.nextInt();
                
        
        try {
            //Validamos
            if (precio <= 0) {
                throw new Exception("El precio no puede ser negativo ni cero");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del producto");
            }
            if (buscarProductoPorCodigo(codigoFabricante) == null) {
                throw new Exception("No existe el fabricante con el codigo " + codigoFabricante);
            }
            if (nombre.length() > 50) {
                throw new Exception("El nombre no puede ser mayor a 50 caracteres");
            }

            //Creamos el producto
            Producto producto = new Producto();
            producto.setPrecio(precio);
            producto.setNombre(nombre);
            producto.setCodigo_fabricante(codigoFabricante);
            dao.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarNombre() throws Exception {
        Scanner r = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Ingrese el codigo del nombre que quiere modificar");
        int codigo = r.nextInt();
        System.out.println("Ingrese el nombre nuevo");
        String nuevoNombre = r.next();

        try {

            //Validamos
            if (codigo < 0) {
                throw new Exception("el codigo no puede ser menor a cero");
            }

            if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nuevo nombre");
            }

            //Buscamos
            Producto producto = dao.buscarProductoPorCodigo(codigo);

            //Validamos
            if (!(producto.getCodigo() == codigo)) {
                throw new Exception("El codigo que ingreso no esta registrado en la base de datos");
            }

            //Modificamos
            producto.setCodigo(codigo);
            producto.setNombre(nuevoNombre);
            dao.modificarNombreProducto(producto);
        } catch(InputMismatchException e) {
            System.out.println(e);
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void modificarPrecio() throws Exception {
        Scanner r = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Ingrese el codigo del producto que quiera modificar");
        int codigo = r.nextInt();
        System.out.println("Ingrese el precio nuevo");
        double nuevoPrecio = r.nextDouble();
        
        try {
            //Validamos
            if (codigo <= 0) {
                throw new Exception("El codigo no puede ser menor o igual a cero");
            }

            //Buscamos
            Producto producto = dao.buscarProductoPorCodigo(codigo);

            //Validamos
            if (!(producto.getCodigo() == codigo)) {
                throw new Exception("El codigo que ingreso no esta registrado en la base de datos");
            }

            //Modificamos
            producto.setCodigo(codigo);
            producto.setPrecio(nuevoPrecio);
            dao.modificarPrecio(producto);
        } catch(InputMismatchException e) {
            System.out.println(e);
        } catch (Exception e) {
            throw e;
        }
    }


    public void eliminarProducto(int codigo) throws Exception {

        try {

            //Validamos 
            if (codigo <= 0) {
                throw new Exception("Debe indicar el nombre");
            }

            dao.eliminarProducto(codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {

        try {

            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            Producto producto = dao.buscarProductoPorNombre(nombre);

            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorCodigo(Integer codigo) throws Exception {

        try {

            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el id");
            }

            Producto producto = dao.buscarProductoPorCodigo(codigo);

            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {

        try {

            Collection<Producto> productos = dao.listarProductos();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = dao.listarProductos();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : productos) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void imprimirNombresPrecios() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = dao.listarNombrePrecioProductos();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : productos) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirNombres() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = dao.listarNombreProductos();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : productos) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirPrecios() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = dao.listarPrecios();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : productos) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirPortatiles() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = dao.listarPortatiles();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : productos) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirMasBarato() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = dao.listarMasBarato();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : productos) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
