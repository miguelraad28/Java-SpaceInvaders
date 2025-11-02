package views;

public class NaveView extends ObjetoImpactado {
    private int naveID;
    private int ancho;
    private int alto;

    public NaveView(int naveID, int x, int y) {
        super(naveID, x, y);
        this.ancho = 50; // Ancho por defecto
        this.alto = 50;  // Alto por defecto
    }

    public int getNaveID() {
        return 0;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
