import exception.CantidadInvalidaException;
import model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiendaServiceTest {

    private TiendaService servicio;
    private Producto producto;

    @BeforeEach
    void setUp() {
        servicio = new TiendaService();
        producto = new Producto(1, "Caña Profesional", "Cañas", 15000);
    }

    // Test 1: el total del carrito se calcula correctamente
    @Test
    void testTotalCarrito() {
        servicio.agregarAlCarrito(1, 2);
        double total = servicio.getCarrito().calcularTotal();
        assertEquals(30000, total);
    }

    // Test 2: lanza excepción si la cantidad es inválida
    @Test
    void testCantidadInvalida() {
        assertThrows(CantidadInvalidaException.class, () -> {
            servicio.agregarAlCarrito(1, -1);
        });
    }

    // Test 3: el descuento por monto aplica correctamente
    @Test
    void testDescuentoPorMonto() {
        DescuentoPorMonto descuento = new DescuentoPorMonto(50000, 10);
        Carrito carrito = new Carrito();
        carrito.agregar(producto, 4); // 4 x 15000 = 60000
        double totalBase = carrito.calcularTotal();
        assertTrue(descuento.aplica(carrito, totalBase));
        assertEquals(6000, descuento.calcularDescuento(carrito, totalBase));
    }

    // Test 4: el descuento por categoría aplica correctamente
    @Test
    void testDescuentoPorCategoria() {
        DescuentoPorCategoria descuento = new DescuentoPorCategoria("Kits", 15);
        Carrito carrito = new Carrito();
        Producto kit = new Producto(8, "Kit para amarrar", "Kits", 30000);
        carrito.agregar(kit, 1);
        double totalBase = carrito.calcularTotal();
        assertTrue(descuento.aplica(carrito, totalBase));
        assertEquals(4500, descuento.calcularDescuento(carrito, totalBase));
    }
}