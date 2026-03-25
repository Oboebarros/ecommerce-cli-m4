import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TiendaService {

    private Catalogo catalogo;
    private Carrito carrito;
    private List<Descuento> descuentos;
    private List<Orden> ordenes;
    private int contadorOrdenes = 1;

    public TiendaService() {
        this.catalogo = new Catalogo();
        this.carrito = new Carrito();
        this.ordenes = new ArrayList<>();
        this.descuentos = new ArrayList<>();
        descuentos.add(new DescuentoPorMonto(50000, 10));
        descuentos.add(new DescuentoPorCategoria("Kits", 15));
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void agregarAlCarrito(int id, int cantidad) {
        model.Producto producto = catalogo.buscarPorId(id);
        if (producto == null) {
            System.out.println("❌ Producto con ID " + id + " no encontrado.");
            return;
        }
        carrito.agregar(producto, cantidad);
        System.out.println("✅ " + producto.getNombre() + " agregado al carrito.");
    }

    public void quitarDelCarrito(int id) {
        boolean eliminado = carrito.quitar(id);
        if (eliminado) {
            System.out.println("✅ Producto eliminado del carrito.");
        } else {
            System.out.println("❌ Producto con ID " + id + " no está en el carrito.");
        }
    }

    public List<Descuento> getDescuentos() {
        return descuentos;
    }

    public void mostrarDescuentosActivos() {
        System.out.println("\n===== 🏷️  DESCUENTOS ACTIVOS =====");
        for (Descuento d : descuentos) {
            System.out.println(d.getDescripcion());
        }
        System.out.println("===================================\n");
    }

    public void confirmarCompra() {
        if (carrito.estaVacio()) {
            System.out.println("❌ No puedes confirmar una compra con el carrito vacío.");
            return;
        }

        double totalBase = carrito.calcularTotal();
        double totalDescuentos = 0;

        System.out.println("\n===== 💰 DETALLE DE DESCUENTOS =====");
        for (Descuento d : descuentos) {
            if (d.aplica(carrito, totalBase)) {
                double monto = d.calcularDescuento(carrito, totalBase);
                totalDescuentos += monto;
                System.out.printf("✅ %s → -$%,.0f%n", d.getDescripcion(), monto);
            }
        }

        if (totalDescuentos == 0) {
            System.out.println("ℹ️  Ningún descuento aplica para esta compra.");
        }
        System.out.println("====================================\n");

        double totalFinal = totalBase - totalDescuentos;

        Orden orden = new Orden(
                contadorOrdenes++,
                Map.copyOf(carrito.getItems()),
                totalBase,
                totalDescuentos,
                totalFinal
        );
        ordenes.add(orden);
        orden.mostrar();
        carrito.vaciar();
    }
}