package Restaurante;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteTest {

    @Test
    public void descuentoVisa() {
        Mesa mesa = new Mesa(1, 4);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();

        assertEquals(303.0, mesa.cerrarMesa(new TarjetaVisa(), Propina.DOS_PORCIENTO));
    }

    @Test
    public void DescuentoMastercard() {
        Mesa mesa = new Mesa(2, 2);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();

        assertEquals(305.0, mesa.cerrarMesa(new TarjetaMastercard(), Propina.TRES_PORCIENTO));
    }

    @Test
    public void DescuentoComarcaPlus() {
        Mesa mesa = new Mesa(3, 6);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();

        assertEquals(309.0, mesa.cerrarMesa(new TarjetaComarcaPlus(), Propina.CINCO_PORCIENTO));
    }

    @Test
    public void DescuentoViedma() {
        Mesa mesa = new Mesa(4, 2);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.confirmarPedido();

        assertEquals(102.0, mesa.cerrarMesa(new TarjetaViedma(), Propina.DOS_PORCIENTO));
    }

    @Test
    public void PedirSinConfirmar() {
        Mesa mesa = new Mesa(5, 4);
        mesa.confirmarPedido();
        assertThrows(RuntimeException.class, () -> {
            mesa.pedir(new ItemPedido(new Bebida(100), 1));
        });
    }

    //Test para subir la cobertura
    @Test
    public void testViedmaConPlatoPrincipal() {
        Mesa mesa = new Mesa(9, 2);
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();
        assertEquals(204.0, mesa.cerrarMesa(new TarjetaViedma(), Propina.DOS_PORCIENTO));
    }

    @Test
    public void testErroresDeValidacion() {
        assertThrows(RuntimeException.class, () -> new Bebida(-10));
        assertThrows(RuntimeException.class, () -> new ItemPedido(new Bebida(100), 0));
    }

    @Test
    public void testNoSePuedeCobrarSinConfirmacion() {
        Mesa mesa = new Mesa(10, 2);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        assertThrows(RuntimeException.class, () -> {
            mesa.cerrarMesa(new TarjetaVisa(), Propina.DOS_PORCIENTO);
        });
    }
}