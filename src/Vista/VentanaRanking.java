package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Modelo.Puntuacion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRanking extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaRanking dialog = new VentanaRanking(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param i 
	 * @param arrayList 
	 */
	public VentanaRanking(ArrayList<Puntuacion> puntuaciones, int dificultad) {
		String pnt1 = "";
		String pnt2 = "";
		String pnt3 = "";
		
		ArrayList<Puntuacion> dif1 = new ArrayList<>();
		ArrayList<Puntuacion> dif2 = new ArrayList<>();
		ArrayList<Puntuacion> dif3 = new ArrayList<>();
		
		for (Puntuacion puntuacion : puntuaciones) {
			switch (puntuacion.getDificultad()) {
			case 1:
				dif1.add(puntuacion);
				break;
			case 2:
				dif2.add(puntuacion);
				break;
			case 3:
				dif3.add(puntuacion);
				break;
			}
		}
		
		for (int i = 0; i < dif1.size(); i++) {
			pnt1 += i + 1 + "º - " + dif1.get(i).getNombre() + " : " + dif1.get(i).getPuntuacion() + "\n";
		}
		for (int i = 0; i < dif2.size(); i++) {
			pnt2 += i + 1 + "º - " + dif2.get(i).getNombre() + " : " + dif2.get(i).getPuntuacion() + "\n";
		}
		for (int i = 0; i < dif3.size(); i++) {
			pnt3 += i + 1 + "º - " + dif3.get(i).getNombre() + " : " + dif3.get(i).getPuntuacion() + "\n";
		}
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("RANKING");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{141, 141, 141, 0};
			gbl_panel.rowHeights = new int[]{189, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			if(dificultad == 1 || dificultad == 0){	
				JPanel plDif1 = new JPanel();
				GridBagConstraints gbc_plDif1 = new GridBagConstraints();
				gbc_plDif1.fill = GridBagConstraints.BOTH;
				gbc_plDif1.insets = new Insets(0, 0, 0, 5);
				gbc_plDif1.gridx = 0;
				gbc_plDif1.gridy = 0;
				panel.add(plDif1, gbc_plDif1);
				plDif1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("Dificultad 1");
					plDif1.add(lblNewLabel_1, BorderLayout.NORTH);
					lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				{
					JTextPane lbListaDif1 = new JTextPane();
					lbListaDif1.setText(pnt1);
					lbListaDif1.setEditable(false);
					plDif1.add(lbListaDif1, BorderLayout.CENTER);
				}
			}
			if(dificultad == 2 || dificultad == 0){
				JPanel plDif2 = new JPanel();
				GridBagConstraints gbc_plDif2 = new GridBagConstraints();
				gbc_plDif2.fill = GridBagConstraints.BOTH;
				gbc_plDif2.insets = new Insets(0, 0, 0, 5);
				gbc_plDif2.gridx = 1;
				gbc_plDif2.gridy = 0;
				panel.add(plDif2, gbc_plDif2);
				plDif2.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_2 = new JLabel("Dificultad 2");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
					plDif2.add(lblNewLabel_2, BorderLayout.NORTH);
				}
				{
					JTextPane lbListaDif2 = new JTextPane();
					lbListaDif2.setText(pnt2);
					lbListaDif2.setEditable(false);
					plDif2.add(lbListaDif2, BorderLayout.CENTER);
				}
			}
			if(dificultad == 3 || dificultad == 0){
				JPanel plDif3 = new JPanel();
				GridBagConstraints gbc_plDif3 = new GridBagConstraints();
				gbc_plDif3.fill = GridBagConstraints.BOTH;
				gbc_plDif3.gridx = 2;
				gbc_plDif3.gridy = 0;
				panel.add(plDif3, gbc_plDif3);
				plDif3.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_3 = new JLabel("Dificultad 3");
					lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
					plDif3.add(lblNewLabel_3, BorderLayout.NORTH);
				}
				{
					JTextPane lbListaDif3 = new JTextPane();
					lbListaDif3.setEditable(false);
					lbListaDif3.setText(pnt3);
					plDif3.add(lbListaDif3, BorderLayout.CENTER);
				}
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
