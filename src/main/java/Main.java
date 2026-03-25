import exception.CantidadInvalidaException;
import model.Producto;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static TiendaService servicio = new TiendaService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("  ║      🎵 OboeMarket CLI       ║");
            System.out.println("  ╠══════════════════════════════╣");
            System.out.println("  ║  1) Admin                    ║");
            System.out.println("  ║  2) Usuario                  ║");
            System.out.println("  ║  0) Salir                    ║");
            System.out.println("  ╚══════════════════════════════╝");
            System.out.print("Elige una opción: ");
            opcion = leerInt();
            switch (opcion) {
                case 1 -> menuAdmin();
                case 2 -> menuUsuario();
                case 0 -> System.out.println("👋 ¡Hasta luego!");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void menuAdmin() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        🔧 ADMIN              ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1) Listar productos         ║");
            System.out.println("║  2) Buscar producto          ║");
            System.out.println("║  3) Crear producto           ║");
            System.out.println("║  4) Editar producto          ║");
            System.out.println("║  5) Eliminar producto        ║");
            System.out.println("║  0) Volver                   ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Elige una opción: ");
            opcion = leerInt();
            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> buscarProducto();
                case 3 -> crearProducto();
                case 4 -> editarProducto();
                case 5 -> eliminarProducto();
                case 0 -> System.out.println("↩️  Volviendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void listarProductos() {
        List<Producto> lista = servicio.getCatalogo().listarTodos();
        System.out.println("\n===== 📦 PRODUCTOS =====");
        for (Producto p : lista) {
            System.out.println(p);
        }
        System.out.println("========================\n");
    }

    private static void buscarProducto() {
        System.out.print("Buscar (nombre o categoría): ");
        String texto = scanner.nextLine();
        List<Producto> resultado = servicio.getCatalogo().buscar(texto);
        if (resultado.isEmpty()) {
            System.out.println("❌ No se encontraron productos.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    private static void crearProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();
        if (precio <= 0) {
            System.out.println("❌ El precio debe ser mayor a 0.");
            return;
        }
        int id = servicio.getCatalogo().siguienteId();
        Producto nuevo = new Producto(id, nombre, categoria, precio);
        servicio.getCatalogo().agregar(nuevo);
        System.out.println("✅ Producto creado con ID " + id);
    }

    private static void editarProducto() {
        listarProductos();
        System.out.print("ID del producto a editar: ");
        int id = leerInt();
        Producto p = servicio.getCatalogo().buscarPorId(id);
        if (p == null) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }
        System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isBlank()) p.setNombre(nombre);

        System.out.print("Nueva categoría (" + p.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        if (!categoria.isBlank()) p.setCategoria(categoria);

        System.out.print("Nuevo precio (" + p.getPrecio() + "): ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isBlank()) {
            double precio = Double.parseDouble(precioStr);
            if (precio > 0) p.setPrecio(precio);
        }
        System.out.println("✅ Producto actualizado.");
    }

    private static void eliminarProducto() {
        listarProductos();
        System.out.print("ID del producto a eliminar: ");
        int id = leerInt();
        System.out.print("¿Confirmas eliminar el producto? (s/n): ");
        String confirmacion = scanner.nextLine();
        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = servicio.getCatalogo().eliminar(id);
            System.out.println(eliminado ? "✅ Producto eliminado." : "❌ Producto no encontrado.");
        } else {
            System.out.println("↩️  Eliminación cancelada.");
        }
    }

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        🛒 USUARIO            ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1) Listar productos         ║");
            System.out.println("║  2) Buscar producto          ║");
            System.out.println("║  3) Agregar al carrito       ║");
            System.out.println("║  4) Quitar del carrito       ║");
            System.out.println("║  5) Ver carrito              ║");
            System.out.println("║  6) Ver descuentos activos   ║");
            System.out.println("║  7) Confirmar compra         ║");
            System.out.println("║  0) Volver                   ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Elige una opción: ");
            opcion = leerInt();
            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> buscarProducto();
                case 3 -> agregarAlCarrito();
                case 4 -> quitarDelCarrito();
                case 5 -> servicio.getCarrito().mostrar();
                case 6 -> servicio.mostrarDescuentosActivos();
                case 7 -> servicio.confirmarCompra();
                case 0 -> System.out.println("↩️  Volviendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void agregarAlCarrito() {
        listarProductos();
        System.out.print("ID del producto: ");
        int id = leerInt();
        System.out.print("Cantidad: ");
        int cantidad = leerInt();
        try {
            servicio.agregarAlCarrito(id, cantidad);
        } catch (CantidadInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void quitarDelCarrito() {
        servicio.getCarrito().mostrar();
        System.out.print("ID del producto a quitar: ");
        int id = leerInt();
        servicio.quitarDelCarrito(id);
    }

    private static int leerInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Ingresa un número válido.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Ingresa un número válido.");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}