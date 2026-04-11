package Restaurante;

public class Bebida extends ItemMenu {

    public Bebida(double precio) {
        super(precio);
    }

    @Override
    public double calcularDescuento(Tarjeta tarjeta, int cantidad) {
        return tarjeta.descuentoBebida(this.calcularCostoTotal(cantidad));
    }
}
