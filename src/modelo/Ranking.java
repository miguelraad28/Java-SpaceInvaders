/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Ranking - Gestiona la lista de entradas del ranking
 */
public class Ranking {
    private List<EntradaRanking> entrada;
    
    public Ranking() {
        this.entrada = new ArrayList<>();
    }
    
    /**
     * Envía una nueva entrada al ranking
     */
    public void enviar(String nombre, int puntaje) {
        EntradaRanking entradaExistente = this.buscarPorNombre(nombre);
        
        if (entradaExistente != null) {
            // Si ya existe, actualiza el puntaje si es mayor
            if (entradaExistente.nuevoEsMayor(puntaje)) {
                entradaExistente.actualizarPuntaje(puntaje);
            }
        } else {
            // Si no existe, crea nueva entrada
            this.agregarRanking(new EntradaRanking(nombre, puntaje));
        }
    }
    
    /**
     * Busca una entrada por nombre
     */
    private EntradaRanking buscarPorNombre(String nombre) {
        for (EntradaRanking entrada : this.entrada) {
            if (entrada.soyElRanking(nombre)) {
                return entrada;
            }
        }
        return null;
    }
    
    /**
     * Agrega una nueva entrada al ranking
     */
    private void agregarRanking(EntradaRanking nuevaEntrada) {
        this.entrada.add(nuevaEntrada);
    }
    
    /**
     * Obtiene el mejor puntaje del ranking ???obtenerPuntajes
     */
    public int obtenerMejorPuntaje() {
        int mejorPuntaje = 0;
        for (EntradaRanking entrada : this.entrada) {
            if (entrada.getMayorPuntaje() > mejorPuntaje) {
                mejorPuntaje = entrada.getMayorPuntaje();
            }
        }
        return mejorPuntaje;
    }
    
    /**
     * Obtiene todas las entradas del ranking
     */
    public List<EntradaRanking> MostrarRanking() {
        return new ArrayList<>(this.entrada);
    }
}
