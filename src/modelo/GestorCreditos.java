/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



/**
 * Gestor de créditos para el sistema de juego
 * Gestiona la carga y consumo de créditos
 */
public class GestorCreditos {
    private int creditosCargados;
    
    public GestorCreditos() {
        this.creditosCargados = 1;
    }
    
    /**
     * Carga una cantidad de créditos
     * @param n Número de créditos a cargar
     */
    public void cargar(int n) {
        if (n > 0) {
            this.creditosCargados += n;
        }
    }
    
    /**
     * Consume un crédito para nuevo juego
     * @return true si se pudo consumir el crédito
     */
    public boolean consumirParaNuevoJuego() {
        if (this.creditosCargados > 0) {
            this.creditosCargados--;
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene el saldo actual de créditos
     * @return número de créditos disponibles
     */
    public int obtenerSaldo() {
        return this.creditosCargados;
    }
    
    /**
     * Reinicia los créditos a cero
     */
    public void reiniciar() {
        this.creditosCargados = 0;
    }
}
