package ConcursoMail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos implements RegistroInscripcion {
    private String url;
    private String usuario;
    private String password;

    public BaseDeDatos(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    public void registrar(String fechaHora, int idParticipante, int idConcurso) {
        String sql = "INSERT INTO inscripciones (fecha_hora, id_participante, id_concurso) VALUES (?, ?, ?)";

        try (Connection conection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement sentencia = conection.prepareStatement(sql)) {

            sentencia.setString(1, fechaHora);
            sentencia.setInt(2, idParticipante);
            sentencia.setInt(3, idConcurso);
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la inscripción en la Base de Datos", e);
        }
    }
}
