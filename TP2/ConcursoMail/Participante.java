package ConcursoMail;

public class Participante {
    private int puntos;
    private int id;
    private String email;

    public Participante(int id, String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email del participante no puede estar vacio");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor a cero");
        }
        this.puntos = 0;
        this.id = id;
        this.email = email;
    }

    public void sumarPuntos(int puntosNuevos) {
        this.puntos += puntosNuevos;
    }

    public int puntosGanados() {
        return this.puntos;
    }

    public int id() {
        return this.id;
    }

    public String email() {
        return this.email;
    }
}
