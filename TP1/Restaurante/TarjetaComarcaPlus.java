package Restaurante;

public class TarjetaComarcaPlus implements Tarjeta {
    @Override
    public double descuentoBebida(double monto) {
        return monto * 0.02;
    }

    @Override
    public double descuentoPlatoPrincipal(double monto) {
        return monto * 0.02;
    }
}
