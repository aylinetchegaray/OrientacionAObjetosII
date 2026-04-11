package ConcursoMail;

public interface Notificador {
    void enviarEmail(String destinatario, String asunto, String cuerpo);
}
