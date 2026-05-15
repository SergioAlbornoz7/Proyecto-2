package pkgmain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI {

	public static void main(String[] args) {
		new MiVentana();

	}

}
class MiVentana extends JFrame{
	private PanelCentral panelCentral;
	private Panel_Oeste panelOeste;
	MiVentana(){
        setBounds(800, 400, 900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Seamos Civilizados");
        init_components();
        setVisible(true);
	}
	public void init_components(){
        panelCentral = new PanelCentral();
        add(panelCentral);
        
        panelOeste = new Panel_Oeste();
        panelOeste.setBackground(Color.BLACK);
        add(panelOeste, BorderLayout.WEST);
	}
}
class PanelCentral extends JPanel {
	private JButton boton1;
	PanelCentral() {
		boton1 = new JButton("Boton 1");
		add(boton1);
	}
}

class Panel_Oeste extends JPanel {
	private JButton boton1,boton2,boton3;
	Panel_Oeste(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		boton1 = new JButton("Boton 1");
		boton2 = new JButton("Boton 2");
		boton3 = new JButton("Boton 3");
		add(boton1);
		add(boton2);
		add(boton3);
		
	}
}