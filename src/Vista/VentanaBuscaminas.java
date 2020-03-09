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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaBuscaminas extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentral;
	private JButton btnNewButton;

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
			panelCentral.setLayout(new GridLayout(Main.partida.getAncho(), Main.partida.getAlto(), 0, 0));
		}

		for (int i = 0; i < Main.partida.getCantCasillas(); i++) {
			JButton btn = new JButton();
			if (Main.partida.esBomba(i)) {
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < Main.partida.getCantCasillas(); i++) {
							if (e.getSource() == panelCentral.getComponent(i)) {
								clickBomba(i);
							}
						}
					}
				});
			} else {
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < Main.partida.getCantCasillas(); i++) {
							if (e.getSource() == panelCentral.getComponent(i)) {
								clickCasilla(i);
							}
						}
					}
				});
			}

			panelCentral.add(btn);
		}
	}

	private void clickCasilla(int posicion) {
		((AbstractButton) panelCentral.getComponent(posicion)).setBackground(Color.green);
		((AbstractButton) panelCentral.getComponent(posicion)).setEnabled(false);

		int bombasCerca = 0;
		int posCont[] = { posicion - Main.partida.getAncho() - 1, posicion - Main.partida.getAncho(),
				posicion - Main.partida.getAncho() + 1, posicion - 1, posicion + 1,
				posicion + Main.partida.getAncho() - 1, posicion + Main.partida.getAncho(),
				posicion + Main.partida.getAncho() + 1 };

		if (posCont[0] < 0 || posCont[0] / Main.partida.getAncho() < posCont[1] / Main.partida.getAncho()) {
			posCont[0] = -1;
		} else {
			if (Main.partida.esBomba(posCont[0])) {
				bombasCerca++;
			}
		}

		if (posCont[1] < 0) {
			posCont[1] = -1;
		} else {
			if (Main.partida.esBomba(posCont[1])) {
				bombasCerca++;
			}
		}

		if (posCont[2] < 0 || posCont[2] / Main.partida.getAncho() > posCont[1] / Main.partida.getAncho()) {
			posCont[2] = -1;
		} else {
			if (Main.partida.esBomba(posCont[2])) {
				bombasCerca++;
			}
		}

		if (posCont[3] < 0 || posCont[3] / Main.partida.getAncho() < posicion / Main.partida.getAncho()) {
			posCont[3] = -1;
		} else {
			if (Main.partida.esBomba(posCont[3])) {
				bombasCerca++;
			}
		}

		if (posCont[4] > Main.partida.getCantCasillas() - 1
				|| posCont[4] / Main.partida.getAncho() > posicion / Main.partida.getAncho()) {
			posCont[4] = -1;
		} else {
			if (Main.partida.esBomba(posCont[4])) {
				bombasCerca++;
			}
		}

		if (posCont[5] > Main.partida.getCantCasillas() - 1
				|| posCont[5] / Main.partida.getAncho() < posCont[6] / Main.partida.getAncho()) {
			posCont[5] = -1;
		} else {
			if (Main.partida.esBomba(posCont[5])) {
				bombasCerca++;
			}
		}

		if (posCont[6] > Main.partida.getCantCasillas() - 1) {
			posCont[6] = -1;
		} else {
			if (Main.partida.esBomba(posCont[6])) {
				bombasCerca++;
			}
		}

		if (posCont[7] > Main.partida.getCantCasillas() - 1
				|| posCont[7] / Main.partida.getAncho() > posCont[6] / Main.partida.getAncho()) {
			posCont[7] = -1;
		} else {
			if (Main.partida.esBomba(posCont[7])) {
				bombasCerca++;
			}
		}

		((AbstractButton) panelCentral.getComponent(posicion)).setText(bombasCerca + "");

		if (bombasCerca == 0) {
			mostrarCasillas(posCont);
		}
	}

	private void clickBomba(int posicion) {
		((AbstractButton) panelCentral.getComponent(posicion)).setBackground(Color.red);
		((AbstractButton) panelCentral.getComponent(posicion)).setEnabled(false);

		javax.swing.JOptionPane.showMessageDialog(this, "CABUMMM");
	}

	private void mostrarCasillas(int[] casillas) {
		for (int i = 0; i < casillas.length; i++) {
			if (casillas[i] != -1) {
				if (((AbstractButton) panelCentral.getComponent(casillas[i])).isEnabled()) {
					((AbstractButton) panelCentral.getComponent(casillas[i])).doClick();
				}
			}
		}
	}
}
