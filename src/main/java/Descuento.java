public interface Descuento {

    double calcularDescuento(Carrito carrito, double totalBase);

    String getDescripcion();

    boolean aplica(Carrito carrito, double totalBase);
}