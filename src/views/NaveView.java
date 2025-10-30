package views;

public class NaveView extends ObjetoImpactado {
    private int naveID;
    private int ancho;
    private int alto;
    private static int vidas;

    public NaveView(int naveID, int x, int y, float vida) {
        super(naveID, x, y);
        this.ancho = 50; // Ancho por defecto
        this.alto = 50;  // Alto por defecto
        int vidas = 3;  // Vidas por defecto
        super.setVida(vida);
    }

    public NaveView(int naveID, int x, int y, int ancho, int alto, int vida, int vidas) {
        super(naveID, x, y);
        this.ancho = ancho;
        this.alto = alto;
        NaveView.vidas = vidas;
        super.setVida(vida);
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

    public static int getVidas() {
        return vidas;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setVidas(int vidas) {
        NaveView.vidas = vidas;
    }

    public static int reducirVida() {
        if (vidas > 0) {
            vidas--;
            return vidas;
        }
        return vidas;
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
