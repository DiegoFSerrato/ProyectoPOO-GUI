package org.example.chessgame.gestion;

import org.example.chessgame.piezas.Pieza;
import org.example.chessgame.piezas.Rey;

public class GestorJuego {
    private Tablero tablero;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private Jugador jugadorActual;

    public GestorJuego() {
        tablero = new Tablero();
        jugadorBlanco = new Jugador("blanco");
        jugadorNegro = new Jugador("negro");
        jugadorActual = jugadorBlanco;
        iniciarJuego();
    }

    public void iniciarJuego() {
        tablero = new Tablero(); // Crear un nuevo tablero para reiniciar las posiciones
        tablero.colocarPiezasIniciales();
        jugadorActual = jugadorBlanco; // Reiniciar el turno al jugador blanco
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getJugadorBlanco() {
        return jugadorBlanco;
    }

    public Jugador getJugadorNegro() {
        return jugadorNegro;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugadorBlanco) ? jugadorNegro : jugadorBlanco;
    }

    public boolean moverPieza(String desde, String hasta) {
        boolean movimientoExitoso = tablero.moverPieza(desde, hasta);
        if (movimientoExitoso) {
            cambiarTurno();
        }
        return movimientoExitoso;
    }

    public boolean estaEnJaque(Jugador jugador) {
        String colorRey = jugador.getColor();
        String posicionRey = buscarRey(colorRey);
        return posicionRey != null && estaBajoAtaque(posicionRey, colorRey);
    }

    public boolean estaEnJaqueMate(Jugador jugador) {
        if (!estaEnJaque(jugador)) {
            return false;
        }

        String colorRey = jugador.getColor();
        for (int filaDesde = 0; filaDesde < 8; filaDesde++) {
            for (int colDesde = 0; colDesde < 8; colDesde++) {
                Pieza pieza = tablero.getPiezaEnPosicion(filaDesde, colDesde);
                if (pieza != null && pieza.getColor().equals(colorRey)) {
                    for (int filaHasta = 0; filaHasta < 8; filaHasta++) {
                        for (int colHasta = 0; colHasta < 8; colHasta++) {
                            String desde = "" + (char) (colDesde + 'a') + (filaDesde + 1);
                            String hasta = "" + (char) (colHasta + 'a') + (filaHasta + 1);
                            if (pieza.esMovimientoValido(desde, hasta, tablero.getTablero())) {
                                Pieza piezaDestino = tablero.getPiezaEnPosicion(hasta);
                                tablero.moverPieza(desde, hasta);
                                boolean enJaque = estaEnJaque(jugador);
                                tablero.moverPieza(hasta, desde);
                                tablero.setPiezaEnPosicion(hasta, piezaDestino);
                                if (!enJaque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private String buscarRey(String colorRey) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = tablero.getPiezaEnPosicion(fila, col);
                if (pieza instanceof Rey && pieza.getColor().equals(colorRey)) {
                    return "" + (char) (col + 'a') + (fila + 1);
                }
            }
        }
        return null;
    }

    private boolean estaBajoAtaque(String posicion, String colorRey) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = tablero.getPiezaEnPosicion(fila, col);
                if (pieza != null && !pieza.getColor().equals(colorRey)) {
                    String desde = "" + (char) (col + 'a') + (fila + 1);
                    if (pieza.esMovimientoValido(desde, posicion, tablero.getTablero())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
