package RestauranteE2;

public abstract class ItemMenu {
    private double precio;

    public ItemMenu(double precio) {
        if (precio <= 0) {
            throw new RuntimeException("El precio es inválido.");
        }
        this.precio = precio;
    }

    public double calcularCostoTotal(int cantidad) {
        return this.precio * cantidad;
    }

    public abstract double calcularDescuento(Tarjeta tarjeta, int cantidad);
}
