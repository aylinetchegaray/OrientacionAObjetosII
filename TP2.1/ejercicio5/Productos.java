package ejercicio5;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    private void ejecutarConPermisos(String userId, Runnable action) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        action.run();
    }

    private <T> T obtenerConPermisos(String userId, Supplier<T> action) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return action.get();
    }

    public void addProducto(String userId, Producto producto) {
        ejecutarConPermisos(userId, () -> this.productos.add(producto));
    }

    public void removeProducto(String userId, Producto producto) {
        ejecutarConPermisos(userId, () -> this.productos.remove(producto));
    }

    public List<Producto> listAll(String userId) {
        return obtenerConPermisos(userId, () -> Collections.unmodifiableList(this.productos));
    }

/*
    public void addProducto(String userId, Producto producto) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.add(producto);
    }

    public void removeProducto(String userId, Producto producto) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.remove(producto);
    }

    public List<Producto> listAll(String userId) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return Collections.unmodifiableList(this.productos);
    }
*/

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}