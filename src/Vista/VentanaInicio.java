package Vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import Controlador.Main;
import Modelo.Partida;
import Vista.VentanaBuscaminas;


public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAceptar;
	private JPanel panel_1;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField tfNombre;
	private SpringLayout sl_panel;
	private JRadioButton dificultad1;
	private JRadioButton dificultad2;
	private JRadioButton dificultad3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	

	public VentanaInicio() {
		initialize();
	}

	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		
       
	}
	
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getBtnAceptar());
			
		}
		return panel;
	}


	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (dificultad1.isSelected()) {
						dispose();
						Main.partida = Partida.getPartida(tfNombre.getText(),1);
						Main.crearVentanaBuscaminas();
					} else if (dificultad2.isSelected()) {
						dispose();
						Main.partida = Partida.getPartida(tfNombre.getText(),2);
						Main.crearVentanaBuscaminas();
					} else if (dificultad3.isSelected()) {
						dispose();
						Main.partida = Partida.getPartida(tfNombre.getText(),3);
						Main.crearVentanaBuscaminas();
					} else {
						javax.swing.JOptionPane.showMessageDialog(VentanaInicio.this, "Debes elegir un tamaño");
					}
				}
			});
		}
		return btnAceptar;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			sl_panel = new SpringLayout();
			panel_1.setLayout(sl_panel);
			panel_1.add(getLblNewLabel());
			panel_1.add(getTfNombre());
			panel_1.add(getLblNewLabel_1());
			panel_1.add(getPanel3());
						
		}
		return panel_1;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Nombre:");
			sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 5, SpringLayout.NORTH, getPanel_1());
			sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 62, SpringLayout.WEST, getPanel_1());
		}
		return lblNewLabel;
	}

	private JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			sl_panel.putConstraint(SpringLayout.EAST, getLblNewLabel(), -14, SpringLayout.WEST, tfNombre);
			sl_panel.putConstraint(SpringLayout.WEST, tfNombre, -270, SpringLayout.EAST, getPanel_1());
			sl_panel.putConstraint(SpringLayout.EAST, tfNombre, -78, SpringLayout.EAST, getPanel_1());
			sl_panel.putConstraint(SpringLayout.NORTH, tfNombre, 2, SpringLayout.NORTH, getPanel_1());
			tfNombre.setColumns(10);
		}
		return tfNombre;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Dificultad:");
			sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 20, SpringLayout.SOUTH, getLblNewLabel());
			sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, getLblNewLabel());
			sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 34, SpringLayout.SOUTH, getLblNewLabel());
			sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -290, SpringLayout.EAST, getPanel_1());
		}
		return lblNewLabel_1;
	}
	
	

	private JRadioButton getRbDificultad1() {
		if ( dificultad1== null) {
			dificultad1 = new JRadioButton("Dificltad 1");
			buttonGroup.add(dificultad1);
		}
		return dificultad1;
	}

	private JRadioButton getRbDificultad2() {
		if (dificultad2 == null) {
			dificultad2 = new JRadioButton("Dificultad 2");
			buttonGroup.add(dificultad2);
		}
		return dificultad2;
	}

	private JRadioButton getRbDificultad3() {
		if (dificultad3 == null) {
			dificultad2 = new JRadioButton("Dificultad 3");
			buttonGroup.add(dificultad2);
		}
		return dificultad2;
	
	}
	
	private JPanel getPanel3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			sl_panel.putConstraint(SpringLayout.NORTH, panel_3, 74, SpringLayout.NORTH, getPanel_1());
			sl_panel.putConstraint(SpringLayout.WEST, panel_3, 166, SpringLayout.WEST, getPanel_1());
			sl_panel.putConstraint(SpringLayout.SOUTH, panel_3, -75, SpringLayout.SOUTH, getPanel_1());
			sl_panel.putConstraint(SpringLayout.EAST, panel_3, -166, SpringLayout.EAST, getPanel_1());
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			panel_3.add(getRbDificultad1());
			panel_3.add(getRbDificultad2());
			panel_3.add(getRbDificultad3());
		}
		return panel_3;
	}
	
	
}
