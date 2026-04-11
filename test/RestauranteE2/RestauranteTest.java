package RestauranteE2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    @Test
    public void descuentoVisa() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(1, 4, registroFake);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();

        assertEquals(303.0, mesa.cerrarMesa(new TarjetaVisa(), Propina.DOS_PORCIENTO, LocalDate.now()));
        assertTrue(registroFake.fueLlamado, "Se debio intentar guardar la venta");
    }

    @Test
    public void DescuentoMastercard() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(2, 2, registroFake);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();

        assertEquals(305.0, mesa.cerrarMesa(new TarjetaMastercard(), Propina.TRES_PORCIENTO, LocalDate.now()));
        assertTrue(registroFake.fueLlamado);
    }

    @Test
    public void DescuentoComarcaPlus() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(3, 6, registroFake);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();

        assertEquals(309.0, mesa.cerrarMesa(new TarjetaComarcaPlus(), Propina.CINCO_PORCIENTO, LocalDate.now()));
        assertTrue(registroFake.fueLlamado);
    }

    @Test
    public void DescuentoViedma() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(4, 2, registroFake);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        mesa.confirmarPedido();

        assertEquals(102.0, mesa.cerrarMesa(new TarjetaViedma(), Propina.DOS_PORCIENTO, LocalDate.now()));
        assertTrue(registroFake.fueLlamado);
    }

    @Test
    public void PedirSinConfirmar() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(5, 4, registroFake);
        mesa.confirmarPedido();
        assertThrows(RuntimeException.class, () -> {
            mesa.pedir(new ItemPedido(new Bebida(100), 1));
        });
    }

    //Test para subir la cobertura
    @Test
    public void testViedmaConPlatoPrincipal() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(9, 2, registroFake);
        mesa.pedir(new ItemPedido(new PlatoPrincipal(200), 1));
        mesa.confirmarPedido();
        assertEquals(204.0, mesa.cerrarMesa(new TarjetaViedma(), Propina.DOS_PORCIENTO, LocalDate.now()));
        assertTrue(registroFake.fueLlamado);
    }

    @Test
    public void testErroresDeValidacion() {
        assertThrows(RuntimeException.class, () -> new Bebida(-10));
        assertThrows(RuntimeException.class, () -> new ItemPedido(new Bebida(100), 0));
    }

    //falla
    @Test
    public void testNoSePuedeCobrarSinConfirmacion() {
        RegistroVentaFake registroFake = new RegistroVentaFake();
        Mesa mesa = new Mesa(10, 2, registroFake);
        mesa.pedir(new ItemPedido(new Bebida(100), 1));
        assertThrows(RuntimeException.class, () -> {
            mesa.cerrarMesa(new TarjetaVisa(), Propina.DOS_PORCIENTO, LocalDate.now());
        });
        assertTrue(registroFake.fueLlamado);
    }
}