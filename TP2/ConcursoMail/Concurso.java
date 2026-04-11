package ConcursoMail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final Notificador notificador;
    private final RegistroInscripcion registro;
    private List<Participante> participantesInscriptos;
    private int idConcurso;

    public Concurso(LocalDate fechaInicio, LocalDate fechaFin, int idConcurso, Notificador notificador, RegistroInscripcion registro) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.participantesInscriptos = new ArrayList<>();
        this.idConcurso = idConcurso;
        this.notificador = notificador;
        this.registro = registro;
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

        String fechaFormateada = fechaInscripcion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.registro.registrar(fechaFormateada, participante.id(), this.idConcurso);
        this.notificador.enviarEmail(participante.email(), "Inscripción", "Se inscribió de forma exitosa al concurso.");
    }

    public boolean perteneceA(Participante participante) {
        return this.participantesInscriptos.contains(participante);
    }
}
