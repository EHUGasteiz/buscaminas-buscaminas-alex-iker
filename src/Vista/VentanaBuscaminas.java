package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBuscaminas extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentral;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelCentral(), BorderLayout.CENTER);
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelCentral;
	}

	public void setLayout(int altura, int longitud) {
		panelCentral.setLayout(new GridLayout(altura, longitud, 0, 0));
		JButton Botones[][] = new JButton[longitud][altura];

		for (int i = 0; i < longitud; i++) {
			for (int j = 0; j < altura; j++) {
				Botones[i][j] = new JButton();
				panelCentral.add(Botones[i][j]);

				Botones[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < longitud; i++) {
							for (int j = 0; j < altura; j++) {
								if (e.getSource() == Botones[i][j]) {
									int casilla = (i * 10) + j + 1;
									boolean mina = false;
									for (int z = 0; z < Main.minas.length && mina == false; z++) {
										if (casilla == Main.minas[z]) {
											mina = true;
										}
									}

									if (mina) {
										mostrarBomba(i, j);
									} else {
										mostrarCasilla(i, j);
									}
								}
							}
						}
					}
				});
			}
		}
	}

	private static void mostrarCasilla(int altura, int longitud) {
		javax.swing.JOptionPane.showMessageDialog(null, "Mostrar numer bombas al rededor: " + altura + "|" + longitud);
	}

	private static void mostrarBomba(int altura, int longitud) {
		javax.swing.JOptionPane.showMessageDialog(null, "La bomba ha explotado: " + altura + "|" + longitud);
	}

}
