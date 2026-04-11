package ConcursoMail;

public class NotificadorFake implements Notificador {
    public boolean fueLlamado = false;

    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo) {
        this.fueLlamado = true;
    }
}