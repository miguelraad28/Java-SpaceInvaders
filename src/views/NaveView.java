package views;

public class NaveView extends ObjetoImpactado {
    private int ancho;
    private int alto;
    private int vidas;

    public NaveView(int naveID, int x, int y, float vida) {
        super(naveID, x, y);
        this.ancho = 50; // Ancho por defecto
        this.alto = 50;  // Alto por defecto
        this.vidas = 3;  // Vidas por defecto
        super.setVida(vida);
    }

    public NaveView(int naveID, int x, int y, int ancho, int alto, float vida, int vidas) {
        super(naveID, x, y);
        this.ancho = ancho;
        this.alto = alto;
        this.vidas = vidas;
        super.setVida(vida);
    }

    public int getNaveID() {
        return super.getId();
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getVidas() {
        return vidas;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void reducirVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    @Override
    public boolean estaDestruido() {
        return vidas <= 0;
    }

    @Override
    public String getTipo() {
        return "Nave";
    }
}
