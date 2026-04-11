package Restaurante;

public class ItemPedido {
    private ItemMenu item;
    private int cantidad;

    public ItemPedido(ItemMenu item, int cantidad) {
        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad es inválida.");
        }
        this.item = item;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return this.item.calcularCostoTotal(this.cantidad);
    }

    public double calcularDescuento(Tarjeta tarjeta) {
        return this.item.calcularDescuento(tarjeta, this.cantidad);
    }
}
