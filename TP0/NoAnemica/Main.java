package NoAnemica;

//Esta clase es no anémica porque porque respeta el principio fundamental de la Orientación a Objetos,
// combina los datos y el proceso dentro de la clase Tiempo. El main actua como una
// capa de aplicacion delgada, que solo coordina y delega el trabajo al objeto de dominio.

public class Main {
    public static void main(String[] args) {
        Tiempo tiempo = new Tiempo();
        tiempo.obtenerFechaCorta();
        tiempo.obtenerFechaLarga();
    }
}