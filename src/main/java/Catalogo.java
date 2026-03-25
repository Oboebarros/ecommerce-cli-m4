import model.Producto;
import java.util.*;

public class Catalogo {

    private List<Producto> productos = new ArrayList<>();

    public Catalogo() {
        productos.add(new Producto(1,  "Caña Profesional",    "Cañas",         15000));
        productos.add(new Producto(2,  "Caña Estudiante",     "Cañas",         10000));
        productos.add(new Producto(3,  "Caja 3 cañas",        "Cajas",          8000));
        productos.add(new Producto(4,  "Caja 6 cañas",        "Cajas",         13000));
        productos.add(new Producto(5,  "Caja 12 cañas",       "Cajas",         22000));
        productos.add(new Producto(6,  "Tubo Chiarugi",       "Accesorios",     3500));
        productos.add(new Producto(7,  "Cuchillo Basics",     "Accesorios",    15000));
        productos.add(new Producto(8,  "Kit para amarrar",    "Kits",          30000));
        productos.add(new Producto(9,  "Kit para raspar",     "Kits",          36000));
        productos.add(new Producto(10, "Organizador Basics",  "Organizadores",  6000));
    }

    public List<Producto> listarTodos() {
        return productos;
    }

    public List<Producto> buscar(String texto) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase()) ||
                    p.getCategoria().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void agregar(Producto p) {
        productos.add(p);
    }

    public boolean eliminar(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

    public void ordenarPorNombre() {
        productos.sort(Comparator.comparing(Producto::getNombre));
    }

    public void ordenarPorPrecio() {
        productos.sort(Comparator.comparingDouble(Producto::getPrecio));
    }

    public int siguienteId() {
        return productos.stream().mapToInt(Producto::getId).max().orElse(0) + 1;
    }
}