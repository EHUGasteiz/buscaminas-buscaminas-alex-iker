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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaBuscaminas extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel panelCentral;
	private JPanel panelCabecera;
	private JPanel panelCabIzq;
	private JPanel panelCabCent;
	private JPanel panelCabDer;
	private JButton btnReinicio;
	private JLabel contadorMinas;
	private JLabel contadorBanderas;

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
		contentPane.add(getPanelCabecera(), BorderLayout.NORTH);
		crearTablero();
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelCentral;
	}

	private JLabel getContadorMinas() {
		if (contadorMinas == null) {
			contadorMinas = new JLabel("0");
			contadorMinas.setIcon(new ImageIcon("image\\mina20.png"));
		}
		return contadorMinas;
	}

	private JLabel getContadorBanderas() {
		if (contadorBanderas == null) {
			contadorBanderas = new JLabel("0");
			contadorBanderas.setHorizontalAlignment(SwingConstants.LEFT);
			contadorBanderas.setHorizontalTextPosition(SwingConstants.LEFT);
			contadorBanderas.setIcon(new ImageIcon("image\\bandera20.png"));
		}
		return contadorBanderas;
	}

	private JPanel getPanelCabecera() {
		if (panelCabecera == null) {
			panelCabecera = new JPanel();
			panelCabecera.setLayout(new BorderLayout(0, 0));
			panelCabecera.add(getPanelCabIzq(), BorderLayout.WEST);
			panelCabecera.add(getPanelCabCent(), BorderLayout.CENTER);
			panelCabecera.add(getPanelCabDer(), BorderLayout.EAST);
		}
		return panelCabecera;
	}

	private JPanel getPanelCabIzq() {
		if (panelCabIzq == null) {
			panelCabIzq = new JPanel();
			panelCabIzq.add(getContadorMinas());
		}
		return panelCabIzq;
	}

	private JPanel getPanelCabCent() {
		if (panelCabCent == null) {
			panelCabCent = new JPanel();
			panelCabCent.add(getBtnReinicio());
		}
		return panelCabCent;
	}

	private JPanel getPanelCabDer() {
		if (panelCabDer == null) {
			panelCabDer = new JPanel();
			panelCabDer.add(getContadorBanderas());
		}
		return panelCabDer;
	}

	private JButton getBtnReinicio() {
		if (btnReinicio == null) {
			btnReinicio = new JButton("");
			btnReinicio.setMargin(new Insets(1, 1, 1, 1));
			btnReinicio.setIcon(new ImageIcon("image\\confuso20.png"));
			btnReinicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// implementar reinicio del juego
				}
			});
		}
		return btnReinicio;
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
					Main.partida.clickCasilla(numCasilla);
				}
			});
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getButton() == MouseEvent.BUTTON3) {
						Main.partida.clickBandera(numCasilla);
					}
				}
			});
			panelCentral.add(btn);
		}

		contadorMinas.setText("" + Main.partida.getCantMinas());
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
		// javax.swing.JOptionPane.showMessageDialog(this, mensaje);
		// int opc = javax.swing.JOptionPane.showConfirmDialog(this, mensaje +
		// "\n¿Quieres empezar una partida nueva?");

		String[] opt = new String[2];
		opt[0] = new String("Nueva partida");
		opt[1] = new String("Ver panel");
		int opc = javax.swing.JOptionPane.showOptionDialog(this, mensaje, "¿Que deseas hacer?", 0,
				javax.swing.JOptionPane.INFORMATION_MESSAGE, null, opt, null);
		if (opc == 0) {
			// implementar reinicio del juego
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		DatosObserver data = (DatosObserver) arg;

		switch (data.getAccion()) {
		case 0:
			partidaPerdida();
			mostrarBomba(data.getPosicion());
			break;
		case 1:
			mostrarCasilla(data.getPosicion(), data.getTexto());
			break;
		case 2:
			mostrarMensaje(data.getTexto());
			break;
		case 3:
			partidaGanada();
			mostrarMensaje("Enhorabuena!!!");
			break;
		case 4:
			ponerBandera(data.getPosicion());
			contadorBanderas.setText(data.getTexto());
			break;
		case 5:
			quitarBandera(data.getPosicion());
			contadorBanderas.setText(data.getTexto());
			break;
		}
	}

	private void partidaGanada() {
		btnReinicio.setIcon(new ImageIcon("image\\sonreir20.png"));
	}

	private void partidaPerdida() {
		btnReinicio.setIcon(new ImageIcon("image\\triste20.png"));
	}

	private void ponerBandera(int posicion) {
		((AbstractButton) panelCentral.getComponent(posicion)).setIcon(new ImageIcon("image\\bandera15.png"));
	}

	private void quitarBandera(int posicion) {
		((AbstractButton) panelCentral.getComponent(posicion)).setIcon(null);
	}
}
