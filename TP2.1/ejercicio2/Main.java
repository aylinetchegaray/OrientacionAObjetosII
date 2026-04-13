package ejercicio2;

public class Main {
    public static void main(String[] args) {
        AprendiendoLambdas a = new AprendiendoLambdas();
        //a. un lambda que imprima true si el largo del String es par, false en caso contrario
        a.unMetodo((var -> var.length() % 2 == 0));
        //b. un lambda que imprima true si el String empieza con “a” minúscula, false en caso contrario.
        a.unMetodo(var -> var.startsWith("a"));
    }
}
