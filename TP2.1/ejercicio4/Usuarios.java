package ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuarios {

    private final String jdbcUrl;

    public Usuarios(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    private void ejecutarEnTransaccion(Transaccion transaccion, String errorMessage) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
            connection.setAutoCommit(false);
            try {
                transaccion.ejecutar(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(errorMessage, e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(errorMessage, e);
        }
    }

    public void insertar(String nombre, String email) {
        ejecutarEnTransaccion(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nombre, email) VALUES (?, ?)")) {
                statement.setString(1, nombre);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        }, "Error al insertar usuario");
    }

/*
    public void insertar(String nombre, String email) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nombre, email) VALUES (?, ?)")) {
            connection.setAutoCommit(false);
            statement.setString(1, nombre);
            statement.setString(2, email);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al insertar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }
*/

    public void actualizarEmail(int id, String nuevoEmail) {
        ejecutarEnTransaccion(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE usuarios SET email = ? WHERE id = ?")) {
                statement.setString(1, nuevoEmail);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }, "Error al actualizar usuario");
    }

/*
    public void actualizarEmail(int id, String nuevoEmail) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement("UPDATE usuarios SET email = ? WHERE id = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, nuevoEmail);
            statement.setInt(2, id);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al actualizar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }
*/

    @FunctionalInterface
    private interface Transaccion {
        void ejecutar(Connection conection) throws SQLException;
    }
}