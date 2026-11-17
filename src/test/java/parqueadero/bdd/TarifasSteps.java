package parqueadero.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import parqueadero.CalculadoraTarifas;
import parqueadero.Descuentos;

/** Pasos que conectan las frases de tarifas.feature con el código. */
public class TarifasSteps {

    private String vehiculo;
    private int minutos;
    private int tarifa;

    private String tipoCliente;
    private int cuenta;
    private int valorFinal;

    @Dado("que un {word} estuvo {int} minutos en el parqueadero")
    public void queUnVehiculoEstuvoMinutos(String vehiculo, int minutos) {
        this.vehiculo = vehiculo;
        this.minutos = minutos;
    }

    @Cuando("se calcula la tarifa")
    public void seCalculaLaTarifa() {
        tarifa = new CalculadoraTarifas().calcularTarifa(minutos, vehiculo);
    }

    @Entonces("debe pagar {int} pesos")
    public void debePagar(int esperado) {
        assertEquals(esperado, tarifa);
    }

    @Dado("un cliente {word} con una cuenta de {int} pesos")
    public void unClienteConUnaCuenta(String tipoCliente, int cuenta) {
        this.tipoCliente = tipoCliente;
        this.cuenta = cuenta;
    }

    @Cuando("se aplica su descuento")
    public void seAplicaSuDescuento() {
        valorFinal = new Descuentos().aplicarDescuento(cuenta, tipoCliente);
    }

    @Entonces("el valor final es {int} pesos")
    public void elValorFinalEs(int esperado) {
        assertEquals(esperado, valorFinal);
    }
}
