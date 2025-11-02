package views;

public class InvasorView extends ObjetoImpactado {
    private int ancho;
    private int alto;

    public InvasorView(int invasorID, int x, int y) {
        super(invasorID, x, y);
        this.ancho = 40; // Ancho por defecto
        this.alto = 30;  // Alto por defecto
    }

    public int getInvasorID() {
        return super.getId();
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
