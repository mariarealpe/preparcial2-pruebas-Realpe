package parqueadero;

/** Descuentos según el tipo de cliente. */
public class Descuentos {

    public int aplicarDescuento(int valor, String tipoCliente) {
        return valor * 90 / 100;
    }
}
