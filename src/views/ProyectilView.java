package views;

import java.util.Optional;

public class ProyectilView {
  private int proyectilID;
  private int x;
  private int y;
  private Optional<ObjetoImpactado> objetoImpactado;

  public ProyectilView(int proyectilID, int x, int y, Optional<ObjetoImpactado> objetoImpactado) {
    this.proyectilID = proyectilID;
    this.x = x;
    this.y = y;
    this.objetoImpactado = objetoImpactado;
  }

  public int getProyectilID() {
    return proyectilID;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Optional<ObjetoImpactado> getObjetoImpactado() {
    return objetoImpactado;
  }

  // Métodos de conveniencia para verificar el tipo de objeto impactado
  public boolean impactoMuro() {
    return objetoImpactado.isPresent() && objetoImpactado.get() instanceof MuroView;
  }

  public boolean impactoInvasor() {
    return objetoImpactado.isPresent() && objetoImpactado.get() instanceof InvasorView;
  }

  public boolean impactoNave() {
    return objetoImpactado.isPresent() && objetoImpactado.get() instanceof NaveView;
  }

  public boolean tieneImpacto() {
    return objetoImpactado.isPresent();
  }

  // Métodos para obtener el objeto impactado con casting seguro
  public Optional<MuroView> getMuroImpactado() {
    if (impactoMuro()) {
      return Optional.of((MuroView) objetoImpactado.get());
    }
    return Optional.empty();
  }

  public Optional<InvasorView> getInvasorImpactado() {
    if (impactoInvasor()) {
      return Optional.of((InvasorView) objetoImpactado.get());
    }
    return Optional.empty();
  }

  public Optional<NaveView> getNaveImpactada() {
    if (impactoNave()) {
      return Optional.of((NaveView) objetoImpactado.get());
    }
    return Optional.empty();
  }
}
