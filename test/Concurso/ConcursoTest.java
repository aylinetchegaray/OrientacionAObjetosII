package Concurso;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InscribirFakeConcursoTest {

    @Test
    public void inscribirParticipante() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        Concurso concurso = new Concurso(inicio, fin);
        Participante nuevoParticipante = new Participante();

        LocalDate fechaInscripcion = LocalDate.of(2026, 3, 25);

        concurso.inscribir(nuevoParticipante, fechaInscripcion);

        assertTrue(concurso.perteneceA(nuevoParticipante));
        assertEquals(0, nuevoParticipante.puntosGanados());
    }
}