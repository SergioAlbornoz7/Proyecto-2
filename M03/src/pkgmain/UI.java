package pkgmain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class UI {

	public static void main(String[] args) {
		new MiVentana();

	}

}
class MiVentana extends JFrame{
	private PanelCentral panelCentral;
	private Panel_Oeste panelOeste;
	private Panel_Este panelEste;
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
        
        panelEste=new Panel_Este();
        panelEste.setBackground(Color.black);
        add(panelEste,BorderLayout.EAST);
        
//        panelInferiorCentral=new Panel_Inferior_Central();
//        add(panelInferiorCentral,BorderLayout.SOUTH);
	}
}
class PanelCentral extends JPanel {
	private JButton boton1;
	private Panel_Inferior_Central panelInferiorCentral;
	PanelCentral() {
		
		setLayout(new BorderLayout());
		
		boton1 = new JButton("Boton 1");
		add(boton1,BorderLayout.NORTH);
		
		panelInferiorCentral=new Panel_Inferior_Central();
		add(panelInferiorCentral,BorderLayout.SOUTH);
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

class Panel_Este extends JPanel {
	private JButton boton1,boton2,boton3;
	Panel_Este(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		boton1 = new JButton("Boton 1");
		boton2 = new JButton("Boton 2");
		boton3 = new JButton("Boton 3");
		add(boton1);
		add(boton2);
		add(boton3);
		
	}
}

class Panel_Inferior_Central extends JPanel {
    
    public Panel_Inferior_Central() {
        // 1. Decimos que este panel se organice como una rejilla de 2x5
        // Parámetros: GridLayout(filas, columnas, espacio_horizontal, espacio_vertical)
        setLayout(new GridLayout(2, 5, 10, 10));
        
        // 2. Le ponemos el borde con título centrado
        TitledBorder borde = BorderFactory.createTitledBorder("Zona Inferior (Tropas)");
        borde.setTitleJustification(TitledBorder.CENTER);
        setBorder(borde);
        
        this.setPreferredSize(new Dimension(700, 250));
        
        // 3. Creamos y añadimos los 10 cuadraditos (en este caso, botones)
        for (int i = 1; i <= 10; i++) {
            JButton botonTropa = new JButton("[Reclutar Tropa " + i + "]");
            
            // Opcional: Puedes cambiarles el color de fondo para que se noten más
            botonTropa.setBackground(Color.WHITE); 
            
            // Al usar GridLayout, solo con hacer add(), Java lo mete en la celda que toca
            add(botonTropa);
        }
    }
}