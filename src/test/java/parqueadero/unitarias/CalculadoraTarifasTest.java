package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import parqueadero.CalculadoraTarifas;

/** Pruebas unitarias de calcularTarifa: particiones de equivalencia y valores límite. */
class CalculadoraTarifasTest {

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    @Test
    @DisplayName("Un carro con una hora y un minuto paga dos horas")
    void carroConUnaHoraYUnMinutoPagaDosHoras() {
        assertEquals(6000, calculadora.calcularTarifa(61, "carro"));
    }

    @Test
    @DisplayName("Una moto con media hora paga una hora")
    void motoConMediaHoraPagaUnaHora() {
        assertEquals(1500, calculadora.calcularTarifa(30, "moto"));
    }

    @Test
    @DisplayName("Rechaza un tipo de vehículo desconocido")
    void rechazaUnTipoDeVehiculoDesconocido() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularTarifa(30, "bicicleta"));
    }

    // ---------- Partición: minutos de gracia (0 a 15) ----------

    @ParameterizedTest(name = "{1} con {0} minutos no paga nada")
    @CsvSource({
        "0, carro",
        "15, carro",
        "0, moto",
        "15, moto"
    })
    @DisplayName("Dentro de los minutos de gracia no se paga")
    void dentroDeLaGraciaNoSePaga(int minutos, String tipo) {
        assertEquals(0, calculadora.calcularTarifa(minutos, tipo));
    }

    // ---------- Partición: cobro por hora o fracción ----------

    @ParameterizedTest(name = "{1} con {0} minutos paga {2}")
    @CsvSource({
        "16, carro, 3000",
        "60, carro, 3000",
        "120, carro, 6000",
        "121, carro, 9000",
        "16, moto, 1500",
        "60, moto, 1500",
        "61, moto, 3000",
        "420, moto, 10500"
    })
    @DisplayName("Después de la gracia se cobra cada hora o fracción")
    void despuesDeLaGraciaSeCobraPorHoraOFraccion(int minutos, String tipo, int esperado) {
        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipo));
    }

    // ---------- Partición: tope diario ----------

    @ParameterizedTest(name = "{1} con {0} minutos paga {2}")
    @CsvSource({
        "480, carro, 24000",
        "481, carro, 25000",
        "1440, carro, 25000",
        "421, moto, 12000",
        "481, moto, 12000",
        "1440, moto, 12000"
    })
    @DisplayName("El valor nunca supera el tope diario del vehículo")
    void elValorNoSuperaElTopeDiario(int minutos, String tipo, int esperado) {
        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipo));
    }

    // ---------- Entradas inválidas ----------

    @ParameterizedTest(name = "rechaza {0} minutos")
    @ValueSource(ints = {-1, 1441})
    @DisplayName("Rechaza minutos fuera del rango 0 a 1440")
    void rechazaMinutosFueraDeRango(int minutos) {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcularTarifa(minutos, "carro"));
        assertEquals("Los minutos deben estar entre 0 y 1440", error.getMessage());
    }

    @ParameterizedTest(name = "rechaza el tipo \"{0}\"")
    @ValueSource(strings = {"", "camion", "CARRO"})
    @DisplayName("Rechaza tipos de vehículo que no son carro ni moto")
    void rechazaTiposDeVehiculoNoValidos(String tipo) {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcularTarifa(30, tipo));
        assertEquals("Tipo de vehículo no válido", error.getMessage());
    }
}
