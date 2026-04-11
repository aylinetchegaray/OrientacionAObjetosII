package RestauranteE2;

public class RegistroVentaFake implements RegistroVenta {
    public boolean fueLlamado = false;

    @Override
    public void registrarVenta(String fecha, double montoTotal) {
        this.fueLlamado = true;
    }
}
