public class DescuentoPorCategoria implements Descuento {

    private String categoria;
    private double porcentaje;

    public DescuentoPorCategoria(String categoria, double porcentaje) {
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }

    @Override
    public boolean aplica(Carrito carrito, double totalBase) {
        return carrito.tieneCategoria(categoria);
    }

    @Override
    public double calcularDescuento(Carrito carrito, double totalBase) {
        if (aplica(carrito, totalBase)) {
            return totalBase * (porcentaje / 100);
        }
        return 0;
    }

    @Override
    public String getDescripcion() {
        return String.format("🏷️  Descuento por categoría: %d%% si el carrito tiene productos de '%s'",
                (int) porcentaje, categoria);
    }
}