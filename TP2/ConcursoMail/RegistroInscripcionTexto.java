package ConcursoMail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroInscripcionTexto implements RegistroInscripcion {
    private String rutaArchivo;

    public RegistroInscripcionTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void registrar(String fechaHora, int idParticipante, int idConcurso) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String linea = fechaHora + ", " + idParticipante + ", " + idConcurso;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al intentar escribir en el archivo de texto", e);
        }
    }
}