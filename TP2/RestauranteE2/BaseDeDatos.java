package RestauranteE2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos implements RegistroVenta {
    private String url;
    private String usuario;
    private String passsword;

    public BaseDeDatos(String url, String usuario, String passsword) {
        this.url = url;
        this.usuario = usuario;
        this.passsword = passsword;
    }

    @Override
    public void registrarVenta(String fecha, double montoTotal) {
        String sql = "INSERT INTO ventas(fecha, monto) VALUE (?, ?)";

        try (Connection conection = DriverManager.getConnection(url, usuario, passsword);
             PreparedStatement sentencia = conection.prepareStatement(sql)) {

            sentencia.setString(1, fecha);
            sentencia.setDouble(2, montoTotal);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar venta en BD", e);
        }
    }
}

