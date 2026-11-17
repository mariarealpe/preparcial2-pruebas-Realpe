package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import parqueadero.Descuentos;

/** Pruebas de aplicarDescuento, construidas con TDD. */
class DescuentosTest {

    private final Descuentos descuentos = new Descuentos();

    @Test
    @DisplayName("Un cliente frecuente tiene 10 % de descuento")
    void clienteFrecuenteTieneDiezPorCiento() {
        assertEquals(2700, descuentos.aplicarDescuento(3000, "frecuente"));
    }

    @Test
    @DisplayName("Un cliente con convenio tiene 20 % de descuento")
    void clienteConvenioTieneVeintePorCiento() {
        assertEquals(20000, descuentos.aplicarDescuento(25000, "convenio"));
    }
}
