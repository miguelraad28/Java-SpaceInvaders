package views;

/**
 * Clase base para todos los objetos que pueden ser impactados por proyectiles
 */
public abstract class ObjetoImpactado {
    protected int id;
    protected int x;
    protected int y;
    protected float vida;

    public ObjetoImpactado(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vida = 1.0f; // Valor por defecto
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getVida() {
        return vida;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    // Métodos abstractos que deben implementar las clases hijas
    public abstract boolean estaDestruido();
    public abstract String getTipo();
}
