package parqueadero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Reporte diario del parqueadero. */
public class Reportes {

    private static final String TOTAL_RECAUDADO = "total_recaudado";

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    /**
     * Cuenta cuántos carros y cuántas motos entraron en el día y suma lo recaudado.
     * Cada estadía se cobra con la misma regla de CalculadoraTarifas.
     */
    public Map<String, Integer> resumen(List<Registro> registros) {
        Map<String, Integer> resumenDelDia = new HashMap<>();
        resumenDelDia.put("carro", 0);
        resumenDelDia.put("moto", 0);
        resumenDelDia.put(TOTAL_RECAUDADO, 0);

        for (Registro registro : registros) {
            int tarifa = calculadora.calcularTarifa(registro.minutos(), registro.tipo());
            resumenDelDia.merge(registro.tipo(), 1, Integer::sum);
            resumenDelDia.merge(TOTAL_RECAUDADO, tarifa, Integer::sum);
        }
        return resumenDelDia;
    }
}
