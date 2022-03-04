
package tienda.servicios;

import tienda.entidades.Fabricante;
import java.util.Collection;
import java.util.Scanner;
import tienda.persistencia.FabricanteDAO;

public class FabricanteService {
    private final FabricanteDAO dao;

    public FabricanteService() {
        this.dao = new FabricanteDAO();
    }

    public void crearFabricante() throws Exception {

        Scanner r = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Ingrese el nombre del fabricante: ");
        String nombre = r.next();
        System.out.println("Ingrese el codigo: ");
        int codigo = r.nextInt();
                
        
        try {
            //Validamos
            if (codigo <= 0) {
                throw new Exception("El codigo no puede ser negativo ni cero");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (buscarFabricantePorCodigo(codigo) != null) {
                throw new Exception("Ya existe un fabricante con el codigo indicado " + codigo);
            }
            if (nombre.length() > 50) {
                throw new Exception("El nombre no puede ser mayor a 50 caracteres");
            }

            //Creamos el fabricante
            Fabricante fabricante = new Fabricante();
            fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);
            dao.guardarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarNombre(String nombre, String nombreActual, String nuevoNombre) throws Exception {

        try {

            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            if (nombreActual == null || nombreActual.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre actual");
            }

            if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nuevo nombre");
            }

            //Buscamos
            Fabricante fabricante = buscarFabricantePorNombre(nombre);

            //Validamos
            if (!fabricante.getNombre().equals(nombreActual)) {
                throw new Exception("El nombre que ingreso no esta registrado en la base de datos");
            }

            //Modificamos
            fabricante.setNombre(nuevoNombre);

            dao.modificarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricante(int codigo) throws Exception {

        try {

            //Validamos 
            if (codigo <= 0) {
                throw new Exception("Debe indicar el nombre");
            }

            dao.eliminarFabricante(codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {

        try {

            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            Fabricante fabricante = dao.buscarFabricantePorNombre(nombre);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {

        try {

            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el id");
            }

            Fabricante fabricante = dao.buscarFabricantePorCodigo(codigo);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricantes() throws Exception {

        try {

            Collection<Fabricante> fabricantes = dao.listarFabricantes();

            return fabricantes;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirFabricante() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Fabricante> fabricantes = listarFabricantes();

            //Imprimimos los usuarios
            if (fabricantes.isEmpty()) {
                throw new Exception("No existen fabricantes para imprimir");
            } else {
                for (Fabricante u : fabricantes) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
