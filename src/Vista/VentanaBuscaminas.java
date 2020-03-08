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
									int casilla = (i * 10) + j;
									boolean mina = false;
									for (int z = 0; z < Main.minas.length && mina == false; z++) {
										if (casilla == Main.minas[z]) {
											mina = true;
										}
									}

									if (mina) {
										mostrarBomba(i, j);
									} else {
										contarBombas(i, j);
									}
								}
							}
						}
					}
				});
			}
		}
	}

	private void contarBombas(int altura, int longitud) {
		int bombasCerca = 0;
		int posiciones[] = { -1, -1, -1, -1, -1, -1, -1, -1 };

		if (altura == 0) {
			if (longitud == 0) {
				// Derecha
				posiciones[0] = (altura * 10) + (longitud + 1);
				// Abajo
				posiciones[1] = ((altura + 1) * 10) + longitud;
				// Derecha + abajo
				posiciones[2] = ((altura + 1) * 10) + (longitud + 1);
			} else if (longitud == Main.alto - 1) {
				// Izquierda
				posiciones[0] = (altura * 10) + (longitud - 1);
				// Abajo
				posiciones[1] = ((altura + 1) * 10) + longitud;
				// Izquierda + abajo
				posiciones[2] = ((altura + 1) * 10) + (longitud - 1);
			} else {
				// Izquierda
				posiciones[0] = (altura * 10) + (longitud - 1);
				// Derecha
				posiciones[1] = (altura * 10) + (longitud + 1);
				// Abajo
				posiciones[2] = ((altura + 1) * 10) + longitud;
				// Izquierda + abajo
				posiciones[3] = ((altura + 1) * 10) + (longitud - 1);
				// Derecha + abajo
				posiciones[4] = ((altura + 1) * 10) + (longitud + 1);
			}
		} else if (altura == Main.ancho - 1) {
			if (longitud == 0) {
				// Derecha
				posiciones[0] = (altura * 10) + (longitud + 1);
				// Arriba
				posiciones[1] = ((altura - 1) * 10) + longitud;
				// Derecha + arriba
				posiciones[2] = ((altura - 1) * 10) + (longitud + 1);
			} else if (longitud == Main.alto - 1) {
				// Izquierda
				posiciones[0] = (altura * 10) + (longitud - 1);
				// Arriba
				posiciones[1] = ((altura - 1) * 10) + longitud;
				// Izquierda + arriba
				posiciones[2] = ((altura - 1) * 10) + (longitud - 1);
			} else {
				// Izquierda
				posiciones[0] = (altura * 10) + (longitud - 1);
				// Derecha
				posiciones[1] = (altura * 10) + (longitud + 1);
				// Arriba
				posiciones[2] = ((altura - 1) * 10) + longitud;
				// Izquierda + arriba
				posiciones[3] = ((altura - 1) * 10) + (longitud - 1);
				// Derecha + arriba
				posiciones[4] = ((altura - 1) * 10) + (longitud + 1);
			}
		} else {
			if (longitud == 0) {
				// Derecha
				posiciones[0] = (altura * 10) + (longitud + 1);
				// Abajo
				posiciones[1] = ((altura + 1) * 10) + longitud;
				// Arriba
				posiciones[2] = ((altura - 1) * 10) + longitud;
				// Derecha + abajo
				posiciones[3] = ((altura + 1) * 10) + (longitud + 1);
				// Derecha + arriba
				posiciones[4] = ((altura - 1) * 10) + (longitud + 1);
			} else if (longitud == Main.alto - 1) {
				// Izquierda
				posiciones[0] = (altura * 10) + (longitud - 1);
				// Abajo
				posiciones[1] = ((altura + 1) * 10) + longitud;
				// Arriba
				posiciones[2] = ((altura - 1) * 10) + longitud;
				// Izquierda + abajo
				posiciones[3] = ((altura + 1) * 10) + (longitud - 1);
				// Izquierda + arriba
				posiciones[4] = ((altura - 1) * 10) + (longitud - 1);
			} else {
				// Izquierda + arriba
				posiciones[0] = (((altura - 1) * 10) + (longitud - 1));
				// Arriba
				posiciones[1] = (((altura - 1) * 10) + longitud);
				// Derecha + arriba
				posiciones[2] = (((altura - 1) * 10) + (longitud + 1));
				// Izquierda
				posiciones[3] = ((altura * 10) + (longitud - 1));
				// Derecha
				posiciones[4] = ((altura * 10) + (longitud + 1));
				// Izquierda + abajo
				posiciones[5] = (((altura + 1) * 10) + (longitud - 1));
				// Abajo
				posiciones[6] = (((altura + 1) * 10) + longitud);
				// Derecha + abajo
				posiciones[7] = (((altura + 1) * 10) + (longitud + 1));
			}
		}

		for (int i = 0; i < posiciones.length; i++) {
			for (int j = 0; j < Main.minas.length; j++) {
				if (posiciones[i] == Main.minas[j]) {
					bombasCerca++;
				}
			}
		}

		((AbstractButton) panelCentral.getComponent(altura * 10 + longitud)).setBackground(Color.GREEN);
		((AbstractButton) panelCentral.getComponent(altura * 10 + longitud)).setEnabled(false);
		((AbstractButton) panelCentral.getComponent(altura * 10 + longitud)).setText(bombasCerca + "");

		// javax.swing.JOptionPane.showMessageDialog(null, "ok");

		if (bombasCerca == 0) {
			mostrarCasillas(altura, longitud);
		}

	}

	private void mostrarCasillas(int altura, int longitud) {
		if (altura == 0) {
			if (longitud == 0) {
				// Derecha
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud + 1))).doClick();
				// Abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + longitud)).doClick();
				// Derecha + abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + (longitud + 1))).doClick();
			} else if (longitud == Main.alto - 1) {
				// Izquierda
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud - 1))).doClick();
				// Abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + longitud)).doClick();
				// Izquierda + abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + (longitud - 1))).doClick();
			} else {
				// Izquierda
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud - 1))).doClick();
				// Derecha
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud + 1))).doClick();
				// Abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + longitud)).doClick();
				// Izquierda + abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + (longitud - 1))).doClick();
				// Derecha + abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + (longitud + 1))).doClick();
			}
		} else if (altura == Main.ancho - 1) {
			if (longitud == 0) {
				// Derecha
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud + 1))).doClick();
				// Arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + longitud)).doClick();
				// Derecha + arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + (longitud + 1))).doClick();
			} else if (longitud == Main.alto - 1) {
				// Izquierda
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud - 1))).doClick();
				// Arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + longitud)).doClick();
				// Izquierda + arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + (longitud - 1))).doClick();
			} else {
				// Izquierda
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud - 1))).doClick();
				// Derecha
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud + 1))).doClick();
				// Arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + longitud)).doClick();
				// Izquierda + arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + (longitud - 1))).doClick();
				// Derecha + arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + (longitud + 1))).doClick();
			}
		} else {
			if (longitud == 0) {
				// Derecha
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud + 1))).doClick();
				// Abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + longitud)).doClick();
				// Arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + longitud)).doClick();
				// Derecha + abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + (longitud + 1))).doClick();
				// Derecha + arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + (longitud + 1))).doClick();
			} else if (longitud == Main.alto - 1) {
				// Izquierda
				((AbstractButton) panelCentral.getComponent((altura * 10) + (longitud - 1))).doClick();
				// Abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + longitud)).doClick();
				// Arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + longitud)).doClick();
				// Izquierda + abajo
				((AbstractButton) panelCentral.getComponent(((altura + 1) * 10) + (longitud - 1))).doClick();
				// Izquierda + arriba
				((AbstractButton) panelCentral.getComponent(((altura - 1) * 10) + (longitud - 1))).doClick();
			} else {
				// Izquierda + arriba
				((AbstractButton) panelCentral.getComponent((((altura - 1) * 10) + (longitud - 1)))).doClick();
				// Arriba
				((AbstractButton) panelCentral.getComponent((((altura - 1) * 10) + longitud))).doClick();
				// Derecha + arriba
				((AbstractButton) panelCentral.getComponent((((altura - 1) * 10) + (longitud + 1)))).doClick();
				// Izquierda
				((AbstractButton) panelCentral.getComponent(((altura * 10) + (longitud - 1)))).doClick();
				// Derecha
				((AbstractButton) panelCentral.getComponent(((altura * 10) + (longitud + 1)))).doClick();
				// Izquierda + abajo
				((AbstractButton) panelCentral.getComponent((((altura + 1) * 10) + (longitud - 1)))).doClick();
				// Abajo
				((AbstractButton) panelCentral.getComponent((((altura + 1) * 10) + longitud))).doClick();
				// Derecha + abajo
				((AbstractButton) panelCentral.getComponent((((altura + 1) * 10) + (longitud + 1)))).doClick();
			}
		}
	}

	private void mostrarBomba(int altura, int longitud) {
		((AbstractButton) panelCentral.getComponent(altura * 10 + longitud)).setBackground(Color.RED);
		((AbstractButton) panelCentral.getComponent(altura * 10 + longitud)).setEnabled(false);
	}

}
