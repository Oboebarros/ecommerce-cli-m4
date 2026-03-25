public class DescuentoPorMonto implements Descuento {

    private double montoMinimo;
    private double porcentaje;

    public DescuentoPorMonto(double montoMinimo, double porcentaje) {
        this.montoMinimo = montoMinimo;
        this.porcentaje = porcentaje;
    }

    @Override
    public boolean aplica(Carrito carrito, double totalBase) {
        return totalBase >= montoMinimo;
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
        return String.format("🏷️  Descuento por monto: %d%% si el total supera $%,.0f",
                (int) porcentaje, montoMinimo);
    }
}