package ConcursoMail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class NotificadorMailtrap implements Notificador {
    private final int puerto;
    private final String host;
    private final String usuario;
    private final String password;

    public NotificadorMailtrap(String host, int puerto, String usuario, String password) {
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", this.host);
        prop.put("mail.smtp.port", String.valueOf(this.puerto));

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistema@unrn.edu.ar"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el mail: " + e.getMessage());
        }
    }
}