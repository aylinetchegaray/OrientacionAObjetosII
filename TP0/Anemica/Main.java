package Anemica;
// Esta forma es anémica debido a que la clase Tiempo no tienen comportamiento,
// solo expone sus datos internos metiante getters (lo que Fowler define como un
// objeto que es una "bolsa de getters y setters"). Toda la lógica de negocio
// ocurre de manera procedimental en el Main.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {
    public static void main(String[] args) {
        Tiempo tiempo = new Tiempo();
        LocalDateTime hoy = tiempo.getFecha();

        DateTimeFormatter formatterCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Fecha en formato corto: " + hoy.format((formatterCorto)));

        DateTimeFormatter formatterLargo = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("Fecha en formato largo: " + hoy.format((formatterLargo)));
    }
}