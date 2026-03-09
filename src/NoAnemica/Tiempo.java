package NoAnemica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Tiempo {
    private LocalDate fecha;

    public Tiempo() {
        this.fecha = LocalDate.now();
    }

    public void obtenerFechaCorta() {
        DateTimeFormatter formatterCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Fecha en formato corto: " + this.fecha.format(formatterCorto));
    }

    public void obtenerFechaLarga() {
        DateTimeFormatter formatterLargo = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("es", "AR"));
        System.out.println("Fecha en formato largo: " + this.fecha.format(formatterLargo));
    }
}
