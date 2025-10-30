package views;

public class InvasorView extends ObjetoImpactado {
    private int ancho;
    private int alto;
    private String tipoInvasor; // Tipo específico de invasor

    public InvasorView(int invasorID, int x, int y, String tipoInvasor) {
        super(invasorID, x, y);
        this.ancho = 40; // Ancho por defecto
        this.alto = 30;  // Alto por defecto
        this.tipoInvasor = "Basico";
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

    public String getTipoInvasor() {
        return tipoInvasor;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setTipoInvasor(String tipoInvasor) {
        this.tipoInvasor = tipoInvasor;
    }

    @Override
    public boolean estaDestruido() {
        return getVida() <= 0;
    }

    @Override
    public String getTipo() {
        return "Invasor";
    }
}
