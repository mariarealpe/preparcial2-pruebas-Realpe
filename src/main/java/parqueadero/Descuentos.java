package parqueadero;

import java.util.Map;

/** Descuentos según el tipo de cliente. */
public class Descuentos {

    private static final int CIEN_POR_CIENTO = 100;
    private static final Map<String, Integer> PORCENTAJE_DE_DESCUENTO =
            Map.of("ninguno", 0, "frecuente", 10, "convenio", 20);

    /**
     * Aplica el descuento del tipo de cliente al valor, en pesos.
     * El resultado se redondea hacia abajo al peso entero.
     */
    public int aplicarDescuento(int valor, String tipoCliente) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        if (!PORCENTAJE_DE_DESCUENTO.containsKey(tipoCliente)) {
            throw new IllegalArgumentException("Tipo de cliente no válido");
        }
        int porcentajeAPagar = CIEN_POR_CIENTO - PORCENTAJE_DE_DESCUENTO.get(tipoCliente);
        return valor * porcentajeAPagar / CIEN_POR_CIENTO;
    }
}
