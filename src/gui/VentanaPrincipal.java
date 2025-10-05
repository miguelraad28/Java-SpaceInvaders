/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 *
 * @author Dell
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        construirVentana();
        construirEventos();
    }

    private void construirVentana() {
        this.setTitle("Space Invaders - POO");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);

        JMenuBar menuBar = construirMenuBar();

        this.setJMenuBar(menuBar);
    }

    private void construirEventos() {
    }

    private JMenuBar construirMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuJuego = new JMenu("Juego");
        JMenuItem nuevoJuego = new JMenuItem("Nuevo Juego");
        //menuJuego.addActionListener(null);
        menuJuego.add(nuevoJuego);

        menuBar.add(menuJuego);
        // Add oter menus.
        /*menuBar.add(menuJuego);
        menuBar.add(menuJuego);
        menuBar.add(menuJuego);*/

        return menuBar;
    }

}
