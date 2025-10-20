/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Rank - Representa una entrada individual en el ranking
 */
public class Rank {
    private String nombre;
    private int mayorPuntaje;
    
    public Rank(String nombre, int puntaje) {
        this.nombre = nombre;
        this.mayorPuntaje = puntaje;
    }
    
    /**
     * Verifica si esta entrada corresponde al nombre dado
     */
    public boolean soyElRanking(String nombre) {
        return this.nombre.equalsIgnoreCase(nombre);
    }
    
    /**
     * Verifica si el nuevo puntaje es mayor que el actual
     */
    public boolean nuevoEsMayor(int nuevoPuntaje) {
        return nuevoPuntaje > this.mayorPuntaje;
    }
    
    /**
     * Actualiza el puntaje si es mayor
     */
    public void actualizarPuntaje(int nuevoPuntaje) {
        if (nuevoPuntaje > this.mayorPuntaje) {
            this.mayorPuntaje = nuevoPuntaje;
        }
    }
    
    // Getters
    public String getNombre() {
        return this.nombre;
    }
    
    public int getMayorPuntaje() {
        return this.mayorPuntaje;
    }
}
