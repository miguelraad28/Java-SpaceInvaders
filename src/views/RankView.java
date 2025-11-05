package views;

public class RankView {
    private final String nombre;
    private final int mayorPuntaje;
    
    public RankView(String nombre, int mayorPuntaje) {
        this.nombre = nombre;
        this.mayorPuntaje = mayorPuntaje;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getMayorPuntaje() {
        return mayorPuntaje;
    }
}
