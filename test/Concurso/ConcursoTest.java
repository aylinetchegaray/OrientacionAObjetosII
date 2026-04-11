package Concurso;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void inscribirParticipante() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        Concurso concurso = new Concurso(inicio, fin);
        Participante nuevoParticipante = new Participante(1);

        LocalDate fechaInscripcion = LocalDate.of(2026, 3, 25);

        concurso.inscribir(nuevoParticipante, fechaInscripcion);

        assertTrue(concurso.perteneceA(nuevoParticipante));
        assertEquals(0, nuevoParticipante.puntosGanados());
    }

    @Test
    public void inscribirPrimerDia() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        Concurso concurso = new Concurso(inicio, fin);
        Participante participante = new Participante(1);

        LocalDate fechaInscripcion = inicio;

        concurso.inscribir(participante, fechaInscripcion);

        assertTrue(concurso.perteneceA(participante));

        assertEquals(10, participante.puntosGanados());
    }

    @Test
    public void inscribirFueraDeRango() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 21);
        Concurso concurso = new Concurso(inicio, fin);
        Participante otroParticipante = new Participante(1);

        LocalDate fechaInvalida = LocalDate.of(2026, 4, 1);

        assertThrows(RuntimeException.class, () -> {
            concurso.inscribir(otroParticipante, fechaInvalida);
        });
    }

    //Test para subir la cobertura
    @Test
    public void inscribirAntesDelInicio() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        Concurso concurso = new Concurso(inicio, fin);
        Participante participante = new Participante(1);

        LocalDate fechaMuyTemprano = inicio.minusDays(1);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            concurso.inscribir(participante, fechaMuyTemprano);
        });

        assertEquals("La inscripción aún no ha comenzado.", exception.getMessage());
    }
}