package model;

public class Producto {

    // Atributos: los datos que describe a un producto
    private int id;
    private String nombre;
    private String categoria;
    private double precio;

    // Constructor: crea un producto con todos sus datos
    public Producto(int id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Getters: para leer los datos
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }

    // Setters: para editar el producto (lo usa el Admin)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setPrecio(double precio) { this.precio = precio; }

    // ToString: cómo se muestra el producto en consola
    @Override
    public String toString() {
        return String.format("ID: %d | %-20s | %-15s | $%.2f",
                id, nombre, categoria, precio);
    }
}