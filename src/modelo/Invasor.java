/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dell
 */
public class Invasor {
  public static int direccion = 1;  
  
  private int x;
  private int y;
  private int ancho;
  private int alto;
  
  public Invasor() {
    // Dimensions must match UI `ImagenInvasor` to align grid spacing
    this.ancho = 53;
    this.alto = 39;
  }

  public int[] mover() {
    int[] resultado = new int[3];

    this.x += direccion;

    resultado[0] = this.x;
    resultado[1] = this.y;
    resultado[2] = 0;
    return resultado;
  }

  public int getX() {
    return this.x;
  }
  public int getY() {
    return this.y;
  }
  public int getAncho() {
    return this.ancho;
  }
  public int getAlto() {
    return this.alto;
  }

  public void mover(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
