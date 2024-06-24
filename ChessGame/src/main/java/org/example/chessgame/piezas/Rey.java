package org.example.chessgame.piezas;

public class Rey extends Pieza {
    public Rey(String color, String imagenURL) {
        super(color, imagenURL);
    }

    @Override
    public boolean esMovimientoValido(String desde, String hasta, Pieza[][] tablero) {
        int desdeFila = Character.getNumericValue(desde.charAt(1)) - 1;
        int desdeCol = desde.charAt(0) - 'a';
        int hastaFila = Character.getNumericValue(hasta.charAt(1)) - 1;
        int hastaCol = hasta.charAt(0) - 'a';

        int deltaFila = Math.abs(hastaFila - desdeFila);
        int deltaCol = Math.abs(hastaCol - desdeCol);

        // El rey puede moverse solo una casilla en cualquier dirección
        if (deltaFila <= 1 && deltaCol <= 1) {
            Pieza piezaDestino = tablero[hastaFila][hastaCol];
            return piezaDestino == null || !piezaDestino.getColor().equals(this.getColor());
        }

        return false; // Movimiento no válido
    }
}
