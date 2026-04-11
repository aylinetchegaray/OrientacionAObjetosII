package RestauranteE2;

public enum Propina {
    DOS_PORCIENTO(0.02),
    TRES_PORCIENTO(0.03),
    CINCO_PORCIENTO(0.05);

    private final double porcentajeCostoTotal;

    Propina(double porcentajeCostoTotal) {
        this.porcentajeCostoTotal = porcentajeCostoTotal;
    }

    public double calcularMonto(double subtotalPedido) {
        return subtotalPedido * this.porcentajeCostoTotal;
    }
}
