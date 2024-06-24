package org.example.chessgame.piezas;

public class Alfil extends Pieza {
    public Alfil(String color, String imagenURL) {
        super(color, imagenURL);
    }

    @Override
    public boolean esMovimientoValido(String desde, String hasta, Pieza[][] tablero) {
        int desdeFila = Character.getNumericValue(desde.charAt(1)) - 1;
        int desdeCol = desde.charAt(0) - 'a';
        int hastaFila = Character.getNumericValue(hasta.charAt(1)) - 1;
        int hastaCol = hasta.charAt(0) - 'a';

        if (Math.abs(hastaFila - desdeFila) != Math.abs(hastaCol - desdeCol)) {
            return false; // Los alfiles se mueven solo en diagonal
        }

        int deltaFila = (hastaFila > desdeFila) ? 1 : -1;
        int deltaCol = (hastaCol > desdeCol) ? 1 : -1;
        int fila = desdeFila + deltaFila;
        int col = desdeCol + deltaCol;

        while (fila != hastaFila && col != hastaCol) {
            if (tablero[fila][col] != null) {
                return false; // Hay una pieza en el camino
            }
            fila += deltaFila;
            col += deltaCol;
        }

        Pieza piezaDestino = tablero[hastaFila][hastaCol];
        return piezaDestino == null || !piezaDestino.getColor().equals(this.getColor());
    }
}
