package RestauranteE2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroVentaTexto implements RegistroVenta {
    private String rutaArchivo;

    public RegistroVentaTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void registrarVenta(String fecha, double montoTotal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String linea = fecha + " || " + montoTotal;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo de ventas", e);
        }
    }
}