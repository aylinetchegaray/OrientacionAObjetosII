package RestauranteE2;

import java.time.LocalDate;

public class MainEjercicio3 {
    public static void main(String[] args) {

        //RegistroVenta registroTxt = new RegistroVentaTexto("ventas_restaurante.txt");
        RegistroVenta registroBD = new BaseDeDatos("jdbc:mysql://localhost:3306/restaurante", "root", "1234");

        Mesa mesa = new Mesa(1, 4, registroBD);

        mesa.pedir(new ItemPedido(new Bebida(3500), 2));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(15000), 1));
        mesa.confirmarPedido();

        try {
            double total = mesa.cerrarMesa(new TarjetaVisa(), Propina.CINCO_PORCIENTO, LocalDate.now());
            System.out.println("Mesa cerrada exitosamente. Total cobrado: $" + total);
        } catch (RuntimeException e) {
            System.out.println("Intento de guardado finalizado. (Nota: " + e.getMessage() + ")");
        }
    }
}
