package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Personas {
    //Refactorizo con metodo privado
    private List<Persona> filtrar(List<Persona> personas, Predicate<Persona> condition) {
        List<Persona> result = new ArrayList<>();
        for (Persona p : personas) {
            if (condition.test(p)) {
                result.add(p);
            }
        }
        return result;
    }

    //filtra la lista de personas devolviendo otra lista con solo aquellas cuyo nombre comienza con E
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        return filtrar(p, persona -> persona.nombre().startsWith("E"));
    }

/*
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (persona.nombre().startsWith("E")) {
                resultado.add(persona);
            }
        }
        return resultado;
    }
*/

    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        return filtrar(p, persona -> persona.nombre().length() % 2 == 0);
    }

/*
    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (persona.nombre().length() % 2 == 0) {
                resultado.add(persona);
            }
        }
        return resultado;
    }
*/
}