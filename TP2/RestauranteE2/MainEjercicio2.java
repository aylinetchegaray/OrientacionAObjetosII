package RestauranteE2;

import java.time.LocalDate;

public class MainEjercicio2 {
    public static void main(String[] args) {
        RegistroVenta registroReal = new RegistroVentaTexto("ventas_restaurante.txt");
        Mesa mesa = new Mesa(1, 4, registroReal);

        mesa.pedir(new ItemPedido(new Bebida(3500), 2));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(15000), 1));
        mesa.confirmarPedido();

        double total = mesa.cerrarMesa(new TarjetaVisa(), Propina.CINCO_PORCIENTO, LocalDate.now());

        System.out.println("Mesa cerrada exitosamente. Total cobrado: $" + total + ". Revisar Txt.");
    }
}