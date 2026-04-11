package Concurso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Participante> participantesInscriptos;

    public Concurso(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.participantesInscriptos = new ArrayList<>();
    }

    public void inscribir(Participante participante, LocalDate fechaInscripcion) {
        if (fechaInscripcion.isBefore(this.fechaInicio)) {
            throw new RuntimeException("La inscripción aún no ha comenzado.");
        }
        if (fechaInscripcion.isAfter(this.fechaFin)) {
            throw new RuntimeException("Fuera de término. El periodo de inscripción finalizó.");
        }
        if (fechaInscripcion.isEqual(fechaInicio)) {
            participante.sumarPuntos(10);
        }
        this.participantesInscriptos.add(participante);
    }

    public boolean perteneceA(Participante participante) {
        return this.participantesInscriptos.contains(participante);
    }
}
