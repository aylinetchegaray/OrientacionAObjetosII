package Anemica;

import java.time.LocalDateTime;

public class Tiempo {
    private LocalDateTime fecha;

    public Tiempo() {
        this.fecha = LocalDateTime.now();
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}