package ConcursoMail;

public class RegistroInscripcionFake implements RegistroInscripcion {
    public boolean fueLlamado = false;

    @Override
    public void registrar(String fechaHora, int idParticipante, int idConcurso) {
        this.fueLlamado = true;
    }
}