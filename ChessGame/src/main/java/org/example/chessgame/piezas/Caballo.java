package org.example.chessgame.piezas;

public class Caballo extends Pieza {
    public Caballo(String color, String imagenURL) {
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

        // El movimiento del caballo es en forma de L (2 y 1 en cualquier orden)
        if (!((deltaFila == 2 && deltaCol == 1) || (deltaFila == 1 && deltaCol == 2))) {
            return false;
        }

        Pieza piezaDestino = tablero[hastaFila][hastaCol];
        return piezaDestino == null || !piezaDestino.getColor().equals(this.getColor());
    }
}
