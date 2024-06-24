package org.example.chessgame.piezas;

public abstract class Pieza {
    private String color;
    private String imagenURL;

    public Pieza(String color, String imagenURL) {
        this.color = color;
        this.imagenURL = imagenURL;
    }

    public String getColor() {
        return color;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public abstract boolean esMovimientoValido(String desde, String hasta, Pieza[][] tablero);
}
