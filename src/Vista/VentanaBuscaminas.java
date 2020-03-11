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
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

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
			JButton btn = new JButton();
			btn.setMargin(new Insets(1, 1, 1, 1));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < Main.partida.getCantCasillas(); i++) {
						if (e.getSource() == panelCentral.getComponent(i)) {
							clickCasilla(i);
						}
					}
				}
			});

			panelCentral.add(btn);
		}
	}

	private void clickCasilla(int posicion) {
		if (Main.partida.esBomba(posicion)) {
			for (int mina : Main.partida.getMinas()) {
				((AbstractButton) panelCentral.getComponent(mina)).setBackground(Color.red);
				((AbstractButton) panelCentral.getComponent(mina)).setEnabled(false);
			}
			javax.swing.JOptionPane.showMessageDialog(this, "CABUMMM");
		} else {
			((AbstractButton) panelCentral.getComponent(posicion)).setBackground(Color.green);
			((AbstractButton) panelCentral.getComponent(posicion)).setEnabled(false);
			List<Integer> casillasMostrar = Main.partida.calcularCasillas(posicion);
			if (casillasMostrar.size() == 1) {
				((AbstractButton) panelCentral.getComponent(posicion)).setText(casillasMostrar.get(0).toString());
			} else {
				for (Integer pos : casillasMostrar) {
					if (((AbstractButton) panelCentral.getComponent(pos)).isEnabled()) {
						clickCasilla(pos);
					}
				}
			}
		}
	}
}
