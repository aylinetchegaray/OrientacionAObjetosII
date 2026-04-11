package ConcursoMail;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void inscribirParticipante() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        NotificadorFake notificador = new NotificadorFake();
        RegistroInscripcionFake registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso(inicio, fin, 1, notificador, registro);
        Participante nuevoParticipante = new Participante(100, "alumno1@unrn.edu.ar");

        LocalDate fechaInscripcion = LocalDate.of(2026, 3, 25);
        concurso.inscribir(nuevoParticipante, fechaInscripcion);

        assertTrue(concurso.perteneceA(nuevoParticipante));
        assertEquals(0, nuevoParticipante.puntosGanados());

        assertTrue(notificador.fueLlamado, "El mail debería haberse enviado");
        assertTrue(registro.fueLlamado, "La inscripción debería haberse registrado");
    }

    @Test
    public void inscribirPrimerDia() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        NotificadorFake notificador = new NotificadorFake();
        RegistroInscripcionFake registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso(inicio, fin, 1, notificador, registro);
        Participante participante = new Participante(101, "alumno2@unrn.edu.ar");

        LocalDate fechaInscripcion = inicio;
        concurso.inscribir(participante, fechaInscripcion);

        assertTrue(concurso.perteneceA(participante));
        assertEquals(10, participante.puntosGanados());
        assertTrue(notificador.fueLlamado);
        assertTrue(registro.fueLlamado);
    }

    @Test
    public void inscribirFueraDeRango() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 21);
        NotificadorFake notificador = new NotificadorFake();
        RegistroInscripcionFake registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso(inicio, fin, 1, notificador, registro);
        Participante otroParticipante = new Participante(102, "tarde@unrn.edu.ar");

        LocalDate fechaInvalida = LocalDate.of(2026, 4, 1);

        assertThrows(RuntimeException.class, () -> concurso.inscribir(otroParticipante, fechaInvalida));

        assertFalse(notificador.fueLlamado);
        assertFalse(registro.fueLlamado);
    }

    @Test
    public void inscribirAntesDelInicio() {
        LocalDate inicio = LocalDate.of(2026, 3, 21);
        LocalDate fin = LocalDate.of(2026, 3, 30);
        NotificadorFake notificador = new NotificadorFake();
        RegistroInscripcionFake registro = new RegistroInscripcionFake();

        Concurso concurso = new Concurso(inicio, fin, 1, notificador, registro);
        Participante participante = new Participante(103, "ansioso@unrn.edu.ar");

        LocalDate fechaMuyTemprano = inicio.minusDays(1);

        Exception exception = assertThrows(RuntimeException.class, () -> concurso.inscribir(participante, fechaMuyTemprano));

        assertEquals("La inscripción aún no ha comenzado.", exception.getMessage());

        assertFalse(notificador.fueLlamado);
        assertFalse(registro.fueLlamado);
    }
}