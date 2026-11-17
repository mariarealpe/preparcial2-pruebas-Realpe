package parqueadero;

/** Descuentos según el tipo de cliente. */
public class Descuentos {

    public int aplicarDescuento(int valor, String tipoCliente) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        if (tipoCliente.equals("ninguno")) {
            return valor;
        }
        if (tipoCliente.equals("convenio")) {
            return valor * 80 / 100;
        }
        if (tipoCliente.equals("frecuente")) {
            return valor * 90 / 100;
        }
        throw new IllegalArgumentException("Tipo de cliente no válido");
    }
}
