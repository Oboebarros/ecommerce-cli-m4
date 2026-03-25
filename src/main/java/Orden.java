import model.Producto;
import java.util.Map;

public class Orden {

    // Atributos de la orden
    private int id;
    private Map<Producto, Integer> items;
    private double totalBase;
    private double totalFinal;
    private double descuentoAplicado;

    // Constructor: recibe todos los datos al confirmar la compra
    public Orden(int id, Map<Producto, Integer> items, double totalBase,
                 double descuentoAplicado, double totalFinal) {
        this.id = id;
        this.items = items;
        this.totalBase = totalBase;
        this.descuentoAplicado = descuentoAplicado;
        this.totalFinal = totalFinal;
    }

    // Getters
    public int getId() { return id; }
    public Map<Producto, Integer> getItems() { return items; }
    public double getTotalBase() { return totalBase; }
    public double getDescuentoAplicado() { return descuentoAplicado; }
    public double getTotalFinal() { return totalFinal; }

    // Mostrar resumen de la orden en consola
    public void mostrar() {
        System.out.println("\n===== ✅ ORDEN #" + id + " CONFIRMADA =====");
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            double subtotal = p.getPrecio() * cantidad;
            System.out.printf("%-25s x%d  $%,.0f%n", p.getNombre(), cantidad, subtotal);
        }
        System.out.println("----------------------------------");
        System.out.printf("TOTAL BASE:        $%,.0f%n", totalBase);
        System.out.printf("DESCUENTO:        -$%,.0f%n", descuentoAplicado);
        System.out.println("----------------------------------");
        System.out.printf("TOTAL FINAL:       $%,.0f%n", totalFinal);
        System.out.println("==================================\n");
    }
}