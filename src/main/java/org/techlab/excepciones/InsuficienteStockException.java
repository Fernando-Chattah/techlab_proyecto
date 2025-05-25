package org.techlab.excepciones;

public class InsuficienteStockException extends Exception {
    public InsuficienteStockException(String mensaje) {
        super(mensaje);
    }
}
