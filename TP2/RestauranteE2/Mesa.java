package RestauranteE2;

import java.time.LocalDate;

public class Mesa {
    private int numero;
    private int capacidad;
    private Pedido pedidoActual;

    public Mesa(int numero, int capacidad, RegistroVenta registro) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.pedidoActual = new Pedido(registro);
    }

    public void pedir(ItemPedido item) {
        this.pedidoActual.agregarItem(item);
    }

    public void confirmarPedido() {
        this.pedidoActual.confirmar();
    }

    public double cerrarMesa(Tarjeta tarjeta, Propina propina, LocalDate fecha) {
        return this.pedidoActual.cobrarPedido(tarjeta, propina, fecha);
    }
}