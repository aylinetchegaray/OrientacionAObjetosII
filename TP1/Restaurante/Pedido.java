package Restaurante;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> items;
    private boolean confirmado;

    public Pedido() {
        this.items = new ArrayList<>();
        this.confirmado = false;
    }

    public void confirmar() {
        this.confirmado = true;
    }

    public void agregarItem(ItemPedido item) {
        if (confirmado) {
            throw new RuntimeException("El pedido ya está confirmado. No puede ser modificado");
        } else {
            items.add(item);
        }
    }

    public double calcularSubtotalSinDescuento() {
        double subtotal = 0;

        for (ItemPedido item : this.items) {
            subtotal = subtotal + item.calcularSubtotal();
        }
        return subtotal;
    }

    public double cobrarPedido(Tarjeta tarjeta, Propina propina) {
        if (!this.confirmado) {
            throw new RuntimeException("Se debe confirmar el pedido antes de pagar.");
        }
        double subtotal = this.calcularSubtotalSinDescuento();
        double descuentoTotal = 0;
        for (ItemPedido item : this.items) {
            descuentoTotal = descuentoTotal + item.calcularDescuento(tarjeta);
        }
        double montoPropina = propina.calcularMonto(subtotal);

        return (subtotal - descuentoTotal) + montoPropina;
    }
}
