package RestauranteE2;

public class TarjetaVisa implements Tarjeta {

    @Override
    public double descuentoBebida(double monto) {
        return monto * 0.03;
    }

    @Override
    public double descuentoPlatoPrincipal(double monto) {
        return 0;
    }
}
