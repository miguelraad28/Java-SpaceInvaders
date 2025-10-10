/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dell
 */
public class GestorCreditos {
    private int creditosCargados = 1;

    public GestorCreditos() {

    }

    public int obtenerSaldo() {
        return this.creditosCargados;
    }

    public boolean puedeIniciarJuego() {
        return this.obtenerSaldo() > 0;
    }

    public void consumirParaNuevoJuego() {
        this.creditosCargados--;
    }
}
