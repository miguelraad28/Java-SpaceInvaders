/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Optional;
import views.InvasorView;

public class Invasor {
  private static int invId = 0;

  private int invasorId;
  private int x;
  private int y;
  private int ancho;
  private int alto;
  private int velocidad;
  private float probabilidadDisparar;
  private int tiempoDesdeUltDisparo;
  private int tiempoRecarga;

  private Area areaJuego;

  public Invasor(int x, int y, int ancho, int alto, int velocidad,
      float probabilidadDisparar, int tiempoRecarga, Area areaJuego) {
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
  }

  public int getInvasorID() {
    return this.invasorId;
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

  public void actualizarCooldownInvasor() {
    if (tiempoDesdeUltDisparo < tiempoRecarga)
      tiempoDesdeUltDisparo++;
  }

  /**
   * Mueve el invasor hacia la derecha o izquierda según la dirección.
   * 
   * @return Un array con la nueva posición del invasor y si tocó el borde.
   *         [0] = nueva posición x
   *         [1] = nueva posición y
   *         [2] = si tocó el borde
   */
  public InvasorView mover(int direccion) {
    if (direccion == 1) {
      this.x += this.velocidad;
    } else {
      this.x -= this.velocidad;
    }

    return new InvasorView(invasorId, this.x, this.y);
  }

  private boolean puedoDisparar() {
    return tiempoDesdeUltDisparo >= tiempoRecarga;
  }

  public Optional<Proyectil> disparar() {
    if (!puedoDisparar())
      return Optional.empty();

    float random = (float) (Math.random() * 100);
    if (random < probabilidadDisparar) {
      tiempoDesdeUltDisparo = 0;

      int px = x + ancho / 2;
      int py = y + alto;
      int velocidadProyectil = 4;

      return Optional.of(new Proyectil(px, py, velocidadProyectil, false));
    }
    return Optional.empty();
  }


  /*
   * Devuelve INvasorView si hay colisión entre el invasor y el proyectil.
   * Devuelve Optional.empty() si no hay colisión.
   */
  public Optional<InvasorView> hayColision(Proyectil proyectil) {
    int px = proyectil.getX();
    int py = proyectil.getY();
    int pAncho = proyectil.getAncho();
    int pAlto = proyectil.getAlto();

    // Verificar colisión horizontal: el proyectil debe estar dentro del ancho del invasor
    boolean colisionX = px + pAncho >= this.x && px <= this.x + this.ancho;
    
    // Verificar colisión vertical: el proyectil debe estar dentro del alto del invasor
    boolean colisionY = py + pAlto >= this.y && py <= this.y + this.alto;

    if (colisionY && colisionX) {
      return Optional.of(new InvasorView(invasorId, this.x, this.y));
    }

    return Optional.empty();
  }
}
