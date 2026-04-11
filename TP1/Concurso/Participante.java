package Concurso;

import java.util.Objects;

public class Participante {
    private int puntos;
    private int id;

    public Participante(int id) {
        this.puntos = 0;
        this.id = id;
    }

    public void sumarPuntos(int puntosNuevos) {
        this.puntos += puntosNuevos;
    }

    public int puntosGanados() {
        return this.puntos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participante queComparamos = (Participante) o;
        return this.id == queComparamos.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
