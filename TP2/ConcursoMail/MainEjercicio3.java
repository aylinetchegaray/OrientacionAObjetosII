package ConcursoMail;

import java.time.LocalDate;

public class MainEjercicio3 {
    public static void main(String[] args) {
        Notificador notificador = new NotificadorMailtrap("sandbox.smtp.mailtrap.io", 2525,
                "usuario_mt", "password_mailtrap");

        //RegistroInscripcion registro = new RegistroInscripcionTexto("inscripciones.txt");
        RegistroInscripcion registroBD = new BaseDeDatos("jdbc:mysql://localhost:3306/concursos", "root", "1234");

        LocalDate inicio = LocalDate.now().minusDays(2);
        LocalDate fin = LocalDate.now().plusDays(10);

        Concurso concurso = new Concurso(inicio, fin, 1, notificador, registroBD);
        Participante p1 = new Participante(100, "ayline@unrn.edu.ar");

        try {
            concurso.inscribir(p1, LocalDate.now());
            System.out.println("Inscripción exitosa. Mail enviado y guardado en la BD.");
        } catch (RuntimeException e) {
            System.out.println("Finalizó el intento de inscripción. (" + e.getMessage() + ")");
        }
    }
}
