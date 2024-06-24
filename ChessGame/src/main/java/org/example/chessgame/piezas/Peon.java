package org.example.chessgame.piezas;

public class Peon extends Pieza {
    public Peon(String color, String imagenURL) {
        super(color, imagenURL);
    }

    @Override
    public boolean esMovimientoValido(String desde, String hasta, Pieza[][] tablero) {
        int desdeFila = Character.getNumericValue(desde.charAt(1)) - 1;
        int desdeCol = desde.charAt(0) - 'a';
        int hastaFila = Character.getNumericValue(hasta.charAt(1)) - 1;
        int hastaCol = hasta.charAt(0) - 'a';

        Pieza piezaDestino = tablero[hastaFila][hastaCol];

        if (getColor().equals("blanco")) {
            // Movimiento hacia adelante para peones blancos
            if (desdeCol == hastaCol) {
                if (hastaFila == desdeFila + 1 && piezaDestino == null) {
                    return true;
                }
                if (desdeFila == 1 && hastaFila == desdeFila + 2 && piezaDestino == null && tablero[desdeFila + 1][desdeCol] == null) {
                    return true;
                }
            }
            // Captura en diagonal para peones blancos
            if (Math.abs(hastaCol - desdeCol) == 1 && hastaFila == desdeFila + 1 && piezaDestino != null && !piezaDestino.getColor().equals(getColor())) {
                return true;
            }
        } else {
            // Movimiento hacia adelante para peones negros
            if (desdeCol == hastaCol) {
                if (hastaFila == desdeFila - 1 && piezaDestino == null) {
                    return true;
                }
                if (desdeFila == 6 && hastaFila == desdeFila - 2 && piezaDestino == null && tablero[desdeFila - 1][desdeCol] == null) {
                    return true;
                }
            }
            // Captura en diagonal para peones negros
            if (Math.abs(hastaCol - desdeCol) == 1 && hastaFila == desdeFila - 1 && piezaDestino != null && !piezaDestino.getColor().equals(getColor())) {
                return true;
            }
        }
        return false;
    }
}
