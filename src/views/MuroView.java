package views;

public class MuroView extends ObjetoImpactado {
  private final int ancho;
  private final int alto;
  private final float vida;

  public MuroView(int muroID, int x, int y, float vida) {
    super(muroID, x, y);
    this.ancho = 80;
    this.alto = 60;
    this.vida = vida;
    super.setVida(vida); // Establecer la vida en la clase padre
  }

  public int getMuroID() {
    return super.getId();
  }

  public int getAncho() {
    return ancho;
  }

  public int getAlto() {
    return alto;
  }

  @Override
  public float getVida() {
    return vida;
  }
}
