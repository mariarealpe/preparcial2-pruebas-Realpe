package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("Un cliente sin tipo especial no tiene descuento")
    void clienteNingunoNoTieneDescuento() {
        assertEquals(3000, descuentos.aplicarDescuento(3000, "ninguno"));
    }

    @Test
    @DisplayName("Rechaza un tipo de cliente desconocido")
    void rechazaTipoDeClienteDesconocido() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> descuentos.aplicarDescuento(3000, "vip"));
        assertEquals("Tipo de cliente no válido", error.getMessage());
    }
}
