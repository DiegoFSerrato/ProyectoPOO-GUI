package org.example.chessgame.piezas;

public class Reina extends Pieza {
    public Reina(String color, String imagenURL) {
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

        if (deltaFila == deltaCol || desdeFila == hastaFila || desdeCol == hastaCol) {
            int filaPaso = (hastaFila > desdeFila) ? 1 : (hastaFila < desdeFila) ? -1 : 0;
            int colPaso = (hastaCol > desdeCol) ? 1 : (hastaCol < desdeCol) ? -1 : 0;

            int fila = desdeFila + filaPaso;
            int col = desdeCol + colPaso;

            while (fila != hastaFila || col != hastaCol) {
                if (tablero[fila][col] != null) {
                    return false; // Hay una pieza en el camino
                }
                fila += filaPaso;
                col += colPaso;
            }

            Pieza piezaDestino = tablero[hastaFila][hastaCol];
            return piezaDestino == null || !piezaDestino.getColor().equals(this.getColor());
        }
        return false; // Movimiento no válido
    }
}
