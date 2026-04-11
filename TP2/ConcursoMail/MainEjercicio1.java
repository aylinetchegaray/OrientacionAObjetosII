package ConcursoMail;

import java.time.LocalDate;

public class MainEjercicio1 {
    public static void main(String[] args) {
        Notificador notificador = new NotificadorMailtrap("sandbox.smtp.mailtrap.io", 2525,
                "41c1f94b511401", "5d145c5ec62e45");
        RegistroInscripcion registro = new RegistroInscripcionTexto("inscripciones.txt");

        Concurso concurso = new Concurso(LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 30), 1, notificador, registro);
        Participante p1 = new Participante(100, "ayline@unrn.edu.ar");

        concurso.inscribir(p1, LocalDate.now());
        System.out.println("Inscripción realizada. Revisar TXT y Mailtrap.");
    }
}