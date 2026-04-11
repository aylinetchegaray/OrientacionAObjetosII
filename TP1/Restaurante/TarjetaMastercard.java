package Restaurante;

public class TarjetaMastercard implements Tarjeta {
    @Override
    public double descuentoBebida(double monto) {
        return 0;
    }

    @Override
    public double descuentoPlatoPrincipal(double monto) {
        return monto * 0.02;
    }
}
