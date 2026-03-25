import exception.CantidadInvalidaException;
import model.Producto;
import java.util.*;

public class Carrito {

    // Mapa que guarda: producto -> cantidad
    private Map<Producto, Integer> items = new LinkedHashMap<>();

    // Agregar producto al carrito
    public void agregar(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new CantidadInvalidaException("❌ La cantidad debe ser mayor a 0.");
        }
        // Si el producto ya está en el carrito, suma la cantidad
        if (items.containsKey(producto)) {
            items.put(producto, items.get(producto) + cantidad);
        } else {
            items.put(producto, cantidad);
        }
    }

    // Quitar producto del carrito por ID
    public boolean quitar(int id) {
        return items.entrySet().removeIf(e -> e.getKey().getId() == id);
    }

    // Ver si el carrito está vacío
    public boolean estaVacio() {
        return items.isEmpty();
    }

    // Obtener todos los items
    public Map<Producto, Integer> getItems() {
        return items;
    }

    // Calcular el total base
    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }

    // Verificar si el carrito tiene productos de una categoría
    public boolean tieneCategoria(String categoria) {
        for (Producto p : items.keySet()) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) return true;
        }
        return false;
    }

    // Mostrar el carrito en consola
    public void mostrar() {
        if (estaVacio()) {
            System.out.println("🛒 El carrito está vacío.");
            return;
        }
        System.out.println("\n===== 🛒 CARRITO =====");
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            double subtotal = p.getPrecio() * cantidad;
            System.out.printf("%-25s x%d  $%,.0f%n", p.getNombre(), cantidad, subtotal);
        }
        System.out.printf("----------------------%n");
        System.out.printf("TOTAL BASE:           $%,.0f%n", calcularTotal());
        System.out.println("======================\n");
    }

    // Vaciar el carrito (después de confirmar compra)
    public void vaciar() {
        items.clear();
    }
}