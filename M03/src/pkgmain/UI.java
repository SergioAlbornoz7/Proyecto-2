package pkgmain;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
//		try {
//			imagen = ImageIO.read(new File("./src/Icono.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		setIconImage(imagen);
		
        panelCentral = new PanelCentral();
        add(panelCentral, BorderLayout.CENTER);
        
        panelOeste = new Panel_Oeste();
//        panelOeste.setBackground(Color.BLACK);
        add(panelOeste, BorderLayout.WEST);
        
        panelEste=new Panel_Este();
//        panelEste.setBackground(Color.black);
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
	private Panel_Oeste_Inferior panelInferiorCentral;
	private Panel_Oeste_Botones panelBotonesMejora;
	Panel_Oeste(){
		setLayout(new BorderLayout());
		
		panelInferiorCentral=new Panel_Oeste_Inferior();
		add(panelInferiorCentral,BorderLayout.NORTH);
		
		panelBotonesMejora = new Panel_Oeste_Botones();
        add(panelBotonesMejora, BorderLayout.SOUTH);
	}
}

class Panel_Oeste_Inferior extends JPanel{
	private String[] nombres= {
			"Comida",
			"Hierro",
			"Madera",
			"Mana",
	};
	public Panel_Oeste_Inferior() {
        // 1. Decimos que este panel se organice como una rejilla de 2x5
        // Parámetros: GridLayout(filas, columnas, espacio_horizontal, espacio_vertical)
        setLayout(new GridLayout(4, 1, 0, 10));
        
        this.setPreferredSize(new Dimension(200, 400));
        
        // 3. Creamos y añadimos los 10 cuadraditos (en este caso, botones)
        for (int i = 0; i <nombres.length; i++) {
        	ImageIcon iconoRecursoO = new ImageIcon(nombres[i]+".png");
            Image imagenEscalada = iconoRecursoO.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            ImageIcon iconoRecursoF = new ImageIcon(imagenEscalada);
            JLabel imagenRecurso = new JLabel(iconoRecursoF);
            JLabel cantidad = new JLabel("1000");
            
            // Opcional: Puedes cambiarles el color de fondo para que se noten más
            imagenRecurso.setBackground(Color.white); 
            
            // Al usar GridLayout, solo con hacer add(), Java lo mete en la celda que toca
            add(imagenRecurso);
            add(cantidad);
        }
    }
}
class Panel_Oeste_Botones extends JPanel {
    public Panel_Oeste_Botones() {
    	// Cargamos y adaptamos las imagenes de las mejoras para añadirlas a los botones
    	ImageIcon iconoAtaque = new ImageIcon("AttackPlus.png");
    	Image imagenAtaque = iconoAtaque.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoAtaquePlus = new ImageIcon(imagenAtaque);
        ImageIcon iconoDefensa = new ImageIcon("DefensePlus.png");
    	Image imagenDefensa = iconoDefensa.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoDefensaPlus = new ImageIcon(imagenDefensa);
        // 1 fila, 2 columnas, 5 píxeles de separación horizontal, 0 vertical
        setLayout(new GridLayout(1, 2, 5, 0));
        
        // Le damos una altura preferida pequeña para que no se estiren demasiado hacia arriba
        setPreferredSize(new Dimension(100, 100));
        
        JButton btnAtaque = new JButton(iconoAtaquePlus);
        JButton btnDefensa = new JButton(iconoDefensaPlus);
        
        btnAtaque.setBackground(Color.WHITE);
        btnDefensa.setBackground(Color.WHITE);
        
        add(btnAtaque);
        add(btnDefensa);
    }
}





class Panel_Este extends JPanel {
	private Panel_Este_Inferior peinferior;
	Panel_Este(){
		setLayout(new BorderLayout());
		
		peinferior=new Panel_Este_Inferior();
		add(peinferior,BorderLayout.NORTH);
		
		
	}
}
class Panel_Este_Inferior extends JPanel{
	private String[] nombres= {
			"Granja",
			"Carpinteria",
			"Herreria",
			"Torre Magica",
			"Iglesia"
	};
	public Panel_Este_Inferior() {
        // 1. Decimos que este panel se organice como una rejilla de 2x5
        // Parámetros: GridLayout(filas, columnas, espacio_horizontal, espacio_vertical)
        setLayout(new GridLayout(5, 1, 0, 10));
        
        // 2. Le ponemos el borde con título centrado
        TitledBorder borde = BorderFactory.createTitledBorder("Lateral Derecho Estructuras");
        borde.setTitleJustification(TitledBorder.TOP);
        setBorder(borde);
        
        this.setPreferredSize(new Dimension(200, 400));
        
        // 3. Creamos y añadimos los 10 cuadraditos (en este caso, botones)
        for (int i = 0; i <nombres.length; i++) {
            JButton botonEstructura = new JButton(nombres[i]);
            
            // Opcional: Puedes cambiarles el color de fondo para que se noten más
            botonEstructura.setBackground(Color.white); 
            
            // Al usar GridLayout, solo con hacer add(), Java lo mete en la celda que toca
            add(botonEstructura);
        }
    }
	
}

class Panel_Inferior_Central extends JPanel {
	private String[] nombres= {
			"Espadachin",
			"Lancero",
			"Ballesta",
			"Cañon",
			"Mago",
			"Sacerdote",
			"Torre Lanza",
			"Catapulta",
			"Lanzacohetes",
	};
    
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
        for (int i = 0; i<nombres.length; i++) {
            JButton botonTropa = new JButton(nombres[i]);
            
            // Opcional: Puedes cambiarles el color de fondo para que se noten más
            botonTropa.setBackground(Color.WHITE); 
            
            // Al usar GridLayout, solo con hacer add(), Java lo mete en la celda que toca
            add(botonTropa);
        }
    }
}