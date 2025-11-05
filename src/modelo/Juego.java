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

    public int getVidas() {
        return this.vidas;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public Dificultad getDificultad() {
        return this.dificultad;
    }

    public void siguienteNivel(Dificultad dificultad) {
        this.dificultad = dificultad;
        this.sumarPuntaje(200);
    }

    public void iniciar() {
        this.enCurso = true;
    }


    public void sumarPuntaje(int puntaje) {
        int puntajePrevio = this.puntaje;

        this.puntaje += puntaje;

        // Cada 500 puntos se otorga 1 vida extra
        int puntajeViejo = puntajePrevio / 500;
        int puntajeNuevo = this.puntaje / 500;
        if (puntajeNuevo > puntajeViejo) {
            sumarVidas();
        }
    }

    public boolean estoyEnCurso() {
        return this.enCurso;
    }

    public void quitarVida() {
        this.vidas--;
    }

    private void sumarVidas() {
        this.vidas++;
    }
}
