package gui;

import controlador.ControladorJuego;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import views.RankView;

public class PanelRanking extends JPanel {
    private VentanaPrincipal ventanaPrincipal;
    private JButton btnVolver;

    public PanelRanking(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        construirVentana();
        construirEventos();
    }

    public void construirVentana() {
        List<RankView> ranking = ControladorJuego.getInstancia(ventanaPrincipal.getAreaJuego()).obtenerRanking();
        int n = ranking.size() + 2; // título + filas + botón volver

        this.setLayout(new GridLayout(n, 1));

        JLabel lblTitulo = new JLabel("Top jugadores:");
        btnVolver = new JButton("Volver");

        this.add(lblTitulo);
        int pos = 1;
        for (RankView r : ranking) {
            this.add(new JLabel(pos + ". " + r.getNombre() + " - " + r.getMayorPuntaje()));
            pos++;
        }
        this.add(btnVolver);
    }

    public void construirEventos() {
        ManejoBtnVolver mbv = new ManejoBtnVolver();
        btnVolver.addActionListener(mbv);
    }

    public class ManejoBtnVolver implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaPrincipal.mostrarPanelInicial();
        }

    }
}
