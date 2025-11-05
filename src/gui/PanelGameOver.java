package gui;

import controlador.ControladorJuego;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelGameOver extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    private JLabel lblTitulo, lblPuntaje, lblNombre;
    private JTextField txtNombre;
    private JButton btnGuardar, btnCancelar;

    public PanelGameOver(VentanaPrincipal ventanaPrincipal, String mensaje) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        construirUI(mensaje);
        construirEventos();
    }
    
    private void construirUI(String mensaje) {
        setLayout(new GridLayout(5, 1));
        
        int puntaje = ControladorJuego.getInstancia(ventanaPrincipal.getAreaJuego()).obtenerPuntaje();
        
        String titulo = (mensaje == null || mensaje.isEmpty()) ? "Game Over" : mensaje;
        lblTitulo = new JLabel(titulo, JLabel.CENTER);
        lblPuntaje = new JLabel("Tu puntaje: " + puntaje, JLabel.CENTER);
        lblNombre = new JLabel("Ingresa tu nombre para el ranking:", JLabel.CENTER);
        txtNombre = new JTextField();

        JPanel acciones = new JPanel(new GridLayout(1, 2));
        
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        acciones.add(btnGuardar);
        acciones.add(btnCancelar);

        add(lblTitulo);
        add(lblPuntaje);
        add(lblNombre);
        add(txtNombre);
        add(acciones);
    }

    private void construirEventos() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();

                if (nombre.isEmpty()) {
                    ventanaPrincipal.mostrarMensaje("El nombre no puede estar vacío");
                    return;
                }

                ControladorJuego.getInstancia(ventanaPrincipal.getAreaJuego()).registrarPuntaje(nombre);
                ControladorJuego.getInstancia(ventanaPrincipal.getAreaJuego()).resetearEstado();

                ventanaPrincipal.mostrarPanelRanking();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorJuego.getInstancia(ventanaPrincipal.getAreaJuego()).resetearEstado();

                ventanaPrincipal.mostrarPanelInicial();
            }
        });
    }
}
