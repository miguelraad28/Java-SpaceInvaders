package gui;

import controlador.ControladorInvasores;
import controlador.ControladorJuego;
import controlador.ControladorProyectiles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelo.Area;
import modelo.Dificultad;
import views.MuroView;
import views.ProyectilView;

public class PanelJuego extends JPanel {

    private Area areaJuego;
    private VentanaPrincipal ventanaPrincipal;

    private ImagenNave imagenNave;
    private List<UIProyectil> uiProyectiles;
    private List<ImagenInvasor> uiInvasores;
    private List<ImagenMuro> uiMuros;

    private JLabel lblDificultad, lblVidas, lblPuntaje;

    private Timer timer;

    public PanelJuego(Dificultad dificultad, Area areaJuego, VentanaPrincipal ventanaPrincipal) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(areaJuego.getAncho(), areaJuego.getAlto()));

        this.areaJuego = areaJuego;
        this.ventanaPrincipal = ventanaPrincipal;

        imagenNave = new ImagenNave();
        uiInvasores = new ArrayList<>();
        uiProyectiles = new ArrayList<>();
        uiMuros = new ArrayList<>();

        imagenNave.mover(areaJuego.getAncho() / 2 - imagenNave.getAncho() / 2, 500);

        this.add(imagenNave);

        imagenNave.setFocusable(true);
        imagenNave.addKeyListener(new KeyAdapter() {
            int nuevoXNave;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 37) { // Flecha izquierda
                    nuevoXNave = ControladorJuego.getInstancia(areaJuego).moverNaveIzquierda();
                    imagenNave.mover(nuevoXNave, imagenNave.getY());
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 39) { // Flecha derecha
                    nuevoXNave = ControladorJuego.getInstancia(areaJuego).moverNaveDerecha();
                    imagenNave.mover(nuevoXNave, imagenNave.getY());
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == 32) { // Espacio
                    int[] proyectil = ControladorJuego.getInstancia(areaJuego).disparar();

                    if (proyectil != null) {
                        System.out.println("Proyectil: " + proyectil[0] + " " + proyectil[1] + " " + proyectil[2]);
                        UIProyectil uiProyectil = new UIProyectil(proyectil[0], true);
                        uiProyectil.mover(proyectil[1], proyectil[2]);
                        uiProyectiles.add(uiProyectil);
                        add(uiProyectil);
                    }
                }
            }
        });

        // TODO: Hacer que iniciar invasores sea como iniciarMuros, que sea con INvasorView
        iniciarInvasores(dificultad);

        iniciarMuros();

        pintarDatosDePartida();

        iniciarCicloJuego();
    }

    private void iniciarInvasores(Dificultad dificultad) {
        int[][] posiciones = ControladorInvasores.getInstancia(areaJuego).iniciarInvasores(dificultad);

        for (int[] posicion : posiciones) {
            ImagenInvasor invasor = new ImagenInvasor();
            invasor.mover(posicion[0], posicion[1]);
            uiInvasores.add(invasor);
            add(invasor);
        }
    }

    private void iniciarMuros() {
        List<MuroView> muroViews = ControladorJuego.getInstancia(areaJuego).iniciarMuros();

        for (MuroView muroView : muroViews) {
            ImagenMuro imagenMuro = new ImagenMuro();
            imagenMuro.mover(muroView.getX(), muroView.getY());
            uiMuros.add(imagenMuro);
            add(imagenMuro);
        }
    }

    private void iniciarCicloJuego() {
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("Ciclo de juego");
                actualizarCooldownNave();
                actualizarCooldownInvasor();
                actualizarInvasores();
                actualizarProyectiles();

                disparoDeInvasores();

                revalidate();
                repaint();
            }
        });

        timer.start();
    }

    private void detenerCicloJuego() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    private void actualizarCooldownNave() {
        ControladorJuego.getInstancia(areaJuego).actualizarCooldownNave();
    }

    private void actualizarCooldownInvasor() {
        ControladorInvasores.getInstancia(areaJuego).actualizarCooldownInvasor();
    }

    private void disparoDeInvasores() {
        Map<Integer, int[]> proyectilesMap = ControladorInvasores.getInstancia(areaJuego).disparoDeInvasores();
        for (Map.Entry<Integer, int[]> entry : proyectilesMap.entrySet()) {
            UIProyectil uiProyectil = new UIProyectil(entry.getKey(), false);
            uiProyectil.mover(entry.getValue()[0], entry.getValue()[1]);
            uiProyectiles.add(uiProyectil);
            add(uiProyectil);
        }
    }

    private void actualizarProyectiles() {
        List<ProyectilView> proyectilesView = ControladorProyectiles.getInstancia(areaJuego).moverProyectiles();

        // Iterar hacia atrás para poder eliminar elementos de forma segura
        for (int i = uiProyectiles.size() - 1; i >= 0; i--) {
            UIProyectil uiProyectil = uiProyectiles.get(i);
            
            // Buscar el ProyectilView correspondiente por ID
            ProyectilView proyectilView = null;
            for (ProyectilView pv : proyectilesView) {
                if (pv.getProyectilID() == uiProyectil.getProyectilID()) {
                    proyectilView = pv;
                    break;
                }
            }

            // Si no se encontró el proyectil en la lista, significa que fue eliminado
            if (proyectilView == null) {
                uiProyectiles.remove(uiProyectil);
                remove(uiProyectil);
                continue;
            }

            // Si impactó, lo elimino
            if (proyectilView.getObjetoImpactado().isPresent()) {
                uiProyectiles.remove(uiProyectil);
                remove(uiProyectil);

                if (proyectilView.impactoMuro()) {

                } else if (proyectilView.impactoInvasor()) {

                } else if (proyectilView.impactoNave()) {

                }
            } else {
                // Si no impactó, lo muevo
                uiProyectil.mover(proyectilView.getX(), proyectilView.getY());
                System.out.println("Proyectil: " + proyectilView.getX() + " " + proyectilView.getY());
            }
        }
    }

    public void actualizarInvasores() {
        ControladorInvasores.getInstancia(areaJuego).moverInvasores();
    }

    public void solicitarFocoNave() {
        imagenNave.requestFocusInWindow();
    }

    private void pintarDatosDePartida() {
        lblDificultad = new JLabel("Dificultad: " + ControladorJuego.getInstancia(areaJuego).obtenerDificultad());
        lblVidas = new JLabel("Vidas: " + ControladorJuego.getInstancia(areaJuego).obtenerVidas());
        lblPuntaje = new JLabel("Puntaje: " + ControladorJuego.getInstancia(areaJuego).obtenerPuntaje());
        lblDificultad.setBounds(10, 10, 200, 20);
        lblVidas.setBounds(10, 30, 200, 20);
        lblPuntaje.setBounds(10, 50, 200, 20);
        add(lblDificultad);
        add(lblVidas);
        add(lblPuntaje);
    }
}
