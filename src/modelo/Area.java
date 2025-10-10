package modelo;

public class Area {
    private int alto;
    private int ancho;

    public Area(int ancho, int alto) {
        System.out.println("Area creada con ancho: " + ancho + " y alto: " + alto);
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAlto() {
        return this.alto;
    }

    public int getAncho() {
        return this.ancho;
    }
}
