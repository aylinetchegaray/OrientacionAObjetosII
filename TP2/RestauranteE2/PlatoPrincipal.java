package RestauranteE2;

public class PlatoPrincipal extends ItemMenu {

    public PlatoPrincipal(double precio) {
        super(precio);
    }

    @Override
    public double calcularDescuento(Tarjeta tarjeta, int cantidad) {
        return tarjeta.descuentoPlatoPrincipal(this.calcularCostoTotal(cantidad));
    }
}