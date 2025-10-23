package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelRanking extends JPanel {
    private VentanaPrincipal ventanaPrincipal;
    private JButton btnVolver;

    public PanelRanking(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        construirVentana();
        construirEventos();
    }

    public void construirVentana() {
        // TODO: n = length of participants in the ranking
        int n = 2;

        this.setLayout(new GridLayout(n, 1));

        JLabel lblTitulo = new JLabel("Top jugadores:");
        btnVolver = new JButton("Volver");

        this.add(lblTitulo);
        // Add participants.
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
