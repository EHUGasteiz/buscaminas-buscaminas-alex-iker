package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Main;
import Modelo.DatosObserver;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaBuscaminas extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel panelCentral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBuscaminas frame = new VentanaBuscaminas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaBuscaminas() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelCentral(), BorderLayout.CENTER);
		crearTablero();
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelCentral;
	}

	private void crearTablero() {
		if (Main.partida.getAlto() > 0) {
			panelCentral.setLayout(new GridLayout(Main.partida.getAlto(), Main.partida.getAncho(), 0, 0));
		}

		for (int i = 0; i < Main.partida.getCantCasillas(); i++) {
			final int numCasilla = i;
			JButton btn = new JButton();
			btn.setMargin(new Insets(1, 1, 1, 1));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Main.partida.calcularCasillas(numCasilla);
				}
			});
			panelCentral.add(btn);
		}
	}

	private void mostrarCasilla(int posicion, String bombas) {
		((AbstractButton) panelCentral.getComponent(posicion)).setBackground(Color.green);
		((AbstractButton) panelCentral.getComponent(posicion)).setEnabled(false);
		((AbstractButton) panelCentral.getComponent(posicion)).setText(bombas);
	}

	private void mostrarBomba(int posicion) {
		((AbstractButton) panelCentral.getComponent(posicion)).setBackground(Color.red);
		((AbstractButton) panelCentral.getComponent(posicion)).setEnabled(false);
		((AbstractButton) panelCentral.getComponent(posicion)).setIcon(new ImageIcon("image\\mina15.png"));
	}

	private void mostrarMensaje(String mensaje) {
		javax.swing.JOptionPane.showMessageDialog(this, mensaje);
	}

	@Override
	public void update(Observable o, Object arg) {
		DatosObserver data = (DatosObserver) arg;

		switch (data.getAccion()) {
		case 0:
			mostrarBomba(data.getPosicion());
			break;
		case 1:
			mostrarCasilla(data.getPosicion(), data.getTexto());
			break;
		case 2:
			mostrarMensaje(data.getTexto());
			break;
		}
	}
}
