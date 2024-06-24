package org.example.chessgame.gestion;

import org.example.chessgame.piezas.*;

public class Tablero {
    private Pieza[][] tablero;

    public Tablero() {
        tablero = new Pieza[8][8];
        colocarPiezasIniciales();
    }

    public void colocarPiezasIniciales() {
        // Colocar piezas blancas
        tablero[0][0] = new Torre("blanco", "Torre.png");
        tablero[0][1] = new Caballo("blanco", "Caballo.png");
        tablero[0][2] = new Alfil("blanco", "Alfil.png");
        tablero[0][3] = new Reina("blanco", "Reina.png");
        tablero[0][4] = new Rey("blanco", "Rey.png");
        tablero[0][5] = new Alfil("blanco", "Alfil.png");
        tablero[0][6] = new Caballo("blanco", "Caballo.png");
        tablero[0][7] = new Torre("blanco", "Torre.png");
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = new Peon("blanco", "Peon.png");
        }

        // Colocar piezas negras
        tablero[7][0] = new Torre("negro", "TorreNegra.png");
        tablero[7][1] = new Caballo("negro", "CaballoNegro.png");
        tablero[7][2] = new Alfil("negro", "AlfilNegro.png");
        tablero[7][3] = new Reina("negro", "ReinaNegra.png");
        tablero[7][4] = new Rey("negro", "ReyNegro.png");
        tablero[7][5] = new Alfil("negro", "AlfilNegro.png");
        tablero[7][6] = new Caballo("negro", "CaballoNegro.png");
        tablero[7][7] = new Torre("negro", "TorreNegra.png");
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = new Peon("negro", "PeonNegro.png");
        }
    }

    public Pieza getPiezaEnPosicion(int fila, int col) {
        if (fila >= 0 && fila < 8 && col >= 0 && col < 8) {
            return tablero[fila][col];
        }
        return null;
    }

    public Pieza getPiezaEnPosicion(String posicion) {
        int fila = Character.getNumericValue(posicion.charAt(1)) - 1;
        int col = posicion.charAt(0) - 'a';
        return getPiezaEnPosicion(fila, col);
    }

    public void setPiezaEnPosicion(int fila, int col, Pieza pieza) {
        if (fila >= 0 && fila < 8 && col >= 0 && col < 8) {
            tablero[fila][col] = pieza;
        }
    }

    public void setPiezaEnPosicion(String posicion, Pieza pieza) {
        int fila = Character.getNumericValue(posicion.charAt(1)) - 1;
        int col = posicion.charAt(0) - 'a';
        setPiezaEnPosicion(fila, col, pieza);
    }

    public boolean moverPieza(String desde, String hasta) {
        Pieza piezaDesde = getPiezaEnPosicion(desde);
        if (piezaDesde != null && piezaDesde.esMovimientoValido(desde, hasta, tablero)) {
            Pieza piezaHasta = getPiezaEnPosicion(hasta);
            if (piezaHasta == null || !piezaHasta.getColor().equals(piezaDesde.getColor())) {
                setPiezaEnPosicion(hasta, piezaDesde);
                setPiezaEnPosicion(desde, null);
                return true;
            }
        }
        return false;
    }

    public Pieza[][] getTablero() {
        return tablero;
    }
}

