package parqueadero;

/** Descuentos según el tipo de cliente. */
public class Descuentos {

    public int aplicarDescuento(int valor, String tipoCliente) {
        if (tipoCliente.equals("ninguno")) {
            return valor;
        }
        if (tipoCliente.equals("convenio")) {
            return valor * 80 / 100;
        }
        return valor * 90 / 100;
    }
}
