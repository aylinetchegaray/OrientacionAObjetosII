package RestauranteE2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> items;
    private boolean confirmado;
    private RegistroVenta registro;

    public Pedido(RegistroVenta registro) {
        if (registro == null) {
            throw new IllegalArgumentException("El pedido necesita un registro de venta valido");
        }
        this.items = new ArrayList<>();
        this.confirmado = false;
        this.registro = registro;
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

    public double cobrarPedido(Tarjeta tarjeta, Propina propina, LocalDate fecha) {
        if (!this.confirmado) {
            throw new RuntimeException("Se debe confirmar el pedido antes de pagar.");
        }
        double subtotal = this.calcularSubtotalSinDescuento();
        double descuentoTotal = 0;
        for (ItemPedido item : this.items) {
            descuentoTotal = descuentoTotal + item.calcularDescuento(tarjeta);
        }
        double montoPropina = propina.calcularMonto(subtotal);

        double totalFinal = (subtotal - descuentoTotal) + montoPropina;

        String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.registro.registrarVenta(fechaFormateada, totalFinal);

        return totalFinal;
    }
}
