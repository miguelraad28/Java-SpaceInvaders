/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dell
 */
public class Juego {

    private boolean enCurso;
    private int puntaje;
    private int vidas;
    private Dificultad dificultad;

    public Juego(Dificultad dificultad) {
        this.puntaje = 0;
        this.vidas = 3;
        this.dificultad = dificultad;
    }

    public void iniciar() {
        this.enCurso = true;
    }

    public boolean estoyEnCurso() {
        return this.enCurso;
    }
}
