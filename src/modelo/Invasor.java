/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import views.InvasorView;

import java.util.Optional;

public class Invasor {
  private static int direccion = 1;

  private static int invId = 0;

  private int invasorId;
  private int x;
  private int y;
  private int ancho;
  private int alto;
  private int velocidad;
  private int probabilidadDisparar;
  private int tiempoDesdeUltDisparo;
  private int tiempoRecarga;
  private String tipoInvasor;

  private Area areaJuego;

  public Invasor(int x, int y, int ancho, int alto, int velocidad,
      int probabilidadDisparar, int tiempoRecarga, Area areaJuego, String tipoInvasor) {
    this.invasorId = invId++;
    this.x = x;
    this.y = y;
    this.ancho = ancho;
    this.alto = alto;
    this.velocidad = velocidad;
    this.probabilidadDisparar = probabilidadDisparar;
    this.tiempoRecarga = tiempoRecarga;
    this.tiempoDesdeUltDisparo = tiempoRecarga;
    this.areaJuego = areaJuego;
    this.tipoInvasor = tipoInvasor;
  }

  public static void cambiarDireccion() {
    Invasor.direccion = -1 * Invasor.direccion;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getAncho() {
    return this.ancho;
  }

  public int getAlto() {
    return this.alto;
  }

  public String getTipoInvasor() {
    return this.tipoInvasor;
  }

  public void setTipoInvasor(String tipoInvasor) {
    this.tipoInvasor = "Basico";
  }

  public void actualizarCooldownInvasor() {
    if (tiempoDesdeUltDisparo < tiempoRecarga)
      tiempoDesdeUltDisparo++;
  }

  /**
   * Mueve el invasor hacia la derecha o izquierda según la dirección.
   * @return Un array con la nueva posición del invasor y si tocó el borde.
   * [0] = nueva posición x
   * [1] = nueva posición y
   * [2] = si tocó el borde
   */
  public int[] mover() {
    boolean tocoBorde = false;
    
    if(direccion == 1) {
      this.x += this.velocidad;
      // Verificar si tocó el borde derecho: x + ancho >= ancho del área de juego
      if ((this.x + this.ancho) >= this.areaJuego.getAncho()) {
        tocoBorde = true;
      }
    } else {
      this.x -= this.velocidad;
      // Verificar si tocó el borde izquierdo: x <= 0
      if (this.x <= 0) {
        tocoBorde = true;
      }
    }

    return new int[] { x, y, tocoBorde ? 1 : 0 };
  }

  private boolean puedoDisparar() {
    return tiempoDesdeUltDisparo >= tiempoRecarga;
  }

  public Proyectil disparar() {
    if (!puedoDisparar())
      return null;

    int random = (int)(Math.random() * 100);
    if (random < probabilidadDisparar) {
      tiempoDesdeUltDisparo = 0;

      int px = x + ancho / 2;
      int py = y + alto;
      int velocidadProyectil = 4;

      return new Proyectil(px, py, velocidadProyectil, false);
    }
    return null;
  }

  public Optional<InvasorView> hayColision(Proyectil proyectil){

    if (proyectil == null) return null;
    if (!proyectil.esDelJugador()) return null;

    int px = proyectil.getX();
    int py = proyectil.getY();
    int pAncho = proyectil.getAncho();
    int pAlto = proyectil.getAlto();

    boolean colisionX = px < this.x + this.ancho && px + pAncho > this.x;
    boolean colisionY = py < this.y + this.alto && py + pAlto > this.y;

    if (colisionY && colisionX) {
      return Optional.of(new InvasorView(invasorId, this.x, this.y, this.tipoInvasor));
    }
    return null;
  }
}
