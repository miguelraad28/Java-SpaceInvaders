package controlador;

import modelo.GestorCreditos;

public class ControladorCreditos {

  private static ControladorCreditos instancia;

  private GestorCreditos gestorCreditos;

  private ControladorCreditos() {
    this.gestorCreditos = new GestorCreditos();

  }

  public static ControladorCreditos getInstancia() {
      if (instancia == null) {
          instancia = new ControladorCreditos();
      } 
      return instancia;
  }


  public void cargar(int cantidad) {
    this.gestorCreditos.cargar(cantidad);
  }

  public int obtenerSaldo() {
    return this.gestorCreditos.obtenerSaldo();
  }
}