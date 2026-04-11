package Restaurante;

public class Mesa {
    private int numero;
    private int capacidad;
    private Pedido pedidoActual;

    public Mesa(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.pedidoActual = new Pedido();
    }

    public void pedir(ItemPedido item) {
        this.pedidoActual.agregarItem(item);
    }

    public void confirmarPedido() {
        this.pedidoActual.confirmar();
    }

    public double cerrarMesa(Tarjeta tarjeta, Propina propina) {
        return this.pedidoActual.cobrarPedido(tarjeta, propina);
    }
}