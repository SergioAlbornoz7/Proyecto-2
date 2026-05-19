package pkgmain;

import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
    // Reemplazamos el JButton por un JTextArea para los mensajes del juego
    private JTextArea areaRegistro; 
    private JScrollPane scrollRegistro;
    private Panel_Inferior_Central panelInferiorCentral;

    PanelCentral() {
        setLayout(new BorderLayout());
        
        // 1. Creamos el área de texto
        areaRegistro = new JTextArea();
        areaRegistro.setBackground(Color.WHITE); // Tu fondo blanco deseado
        areaRegistro.setEditable(false);         // Evita que el jugador pueda escribir o borrar textos directamente
        areaRegistro.setLineWrap(true);          // Si la línea es muy larga, salta de renglón automáticamente
        areaRegistro.setWrapStyleWord(true);      // Corta por palabras completas, no a mitad de una letra
        
        // Mensaje inicial de prueba
        areaRegistro.setText("--- Bienvenido a Seamos Civilizados ---\n¡El juego ha comenzado!\n");
        
        // 2. Metemos el JTextArea dentro del JScrollPane para que tenga barras de scroll
        scrollRegistro = new JScrollPane(areaRegistro);
        
        // Le ponemos un borde decorativo para que combine con el resto de la interfaz
        TitledBorder borde = BorderFactory.createTitledBorder("Registro de Eventos");
        scrollRegistro.setBorder(borde);
        
        // 3. Lo añadimos en el CENTER. 
        // Al estar en el CENTER ocupará TODO el espacio disponible entre los paneles laterales y el de tropas
        add(scrollRegistro, BorderLayout.CENTER);
        
        // Tu panel de tropas se queda exactamente donde estaba abajo
        panelInferiorCentral = new Panel_Inferior_Central();
        add(panelInferiorCentral, BorderLayout.SOUTH);
    }
    
    // 4. Método público muy útil para mandar mensajes desde otros lados del código
    public void registrarEvento(String mensaje) {
        areaRegistro.append(mensaje + "\n");
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
			"Madera",
			"Mana",
			"Hierro",
	};
	public Panel_Oeste_Inferior() {
        // 1. Decimos que este panel se organice como una rejilla de 2x5
        // Parámetros: GridLayout(filas, columnas, espacio_horizontal, espacio_vertical)
        setLayout(new GridLayout(4, 1, 0, 10));
        
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
class Panel_Oeste_Botones extends JPanel {
    public Panel_Oeste_Botones() {
        // 1 fila, 2 columnas, 5 píxeles de separación horizontal, 0 vertical
        setLayout(new GridLayout(1, 2, 5, 0));
        
        // Le damos una altura preferida pequeña para que no se estiren demasiado hacia arriba
        setPreferredSize(new Dimension(200, 50));
        
        JButton btnAtaque = new JButton("[Mejora Ataque]");
        JButton btnDefensa = new JButton("[Mejora Defensa]");
        
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