package views;

public class MuroView {
  private int MuroID;
  private int x;
  private int y;
  private float vida;

  public MuroView(int MuroID, int x, int y, float vida){
    this.MuroID = MuroID;
    this.x = x;
    this.y = y;
    this.vida = vida;
  }

  public int getMuroID(){
    return this.MuroID;
  }

  public float getVida(){
    return this.vida;
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }
}
