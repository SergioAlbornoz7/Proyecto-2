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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class UI {
	
	public static MiVentana ventana;

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
	
	public void actuInterRecursos() {
	    panelOeste.updateRecursos();
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
	
	public void updateRecursos() {
	    panelInferiorCentral.actualizarRecursos();
	}
}

class Panel_Oeste_Inferior extends JPanel {
	private JLabel comidaCantidad;
    private JLabel hierroCantidad;
    private JLabel maderaCantidad;
    private JLabel manaCantidad;
	public Panel_Oeste_Inferior() {

        setLayout(new GridLayout(4, 1, 0, 10));
        
        this.setPreferredSize(new Dimension(200, 400));
        
        ImageIcon iconoComidaO = new ImageIcon("Comida.png");
        Image comidaEscal = iconoComidaO.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon iconoComidaF = new ImageIcon(comidaEscal);
        JLabel comida = new JLabel(iconoComidaF);
        comidaCantidad = new JLabel("" + Main.player.getFood());
        
        
        ImageIcon iconoHierroO = new ImageIcon("Hierro.png");
        Image hierroEscal = iconoHierroO.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon iconoHierroF = new ImageIcon(hierroEscal);
        JLabel hierro = new JLabel(iconoHierroF);
        hierroCantidad = new JLabel("" + Main.player.getIron());
        
        ImageIcon iconoMaderaO = new ImageIcon("Madera.png");
        Image maderaEscal = iconoMaderaO.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon iconoMaderaF = new ImageIcon(maderaEscal);
        JLabel madera = new JLabel(iconoMaderaF);
        maderaCantidad = new JLabel("" + Main.player.getWood());
        
        ImageIcon iconoManaO = new ImageIcon("Mana.png");
        Image manaEscal = iconoManaO.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon iconoManaF = new ImageIcon(manaEscal);
        JLabel mana = new JLabel(iconoManaF);
        manaCantidad = new JLabel("" + Main.player.getMana());
        
        add(comida);
        add(comidaCantidad);
        add(hierro);
        add(hierroCantidad);
        add(madera);
        add(maderaCantidad);
        add(mana);
        add(manaCantidad);
    }
	
	public void actualizarRecursos() {
        comidaCantidad.setText("" + Main.player.getFood());
        hierroCantidad.setText("" + Main.player.getIron());
        maderaCantidad.setText("" + Main.player.getWood());
        manaCantidad.setText("" + Main.player.getMana());
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
        btnAtaque.addActionListener(e -> System.out.println("[CLICK] Pulsaste botonAtaque (Debería ser Ataque)"));
        btnDefensa.addActionListener(e -> System.out.println("[CLICK] Pulsaste botonDefensa (Debería ser Defensa)"));

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
class Panel_Este_Inferior extends JPanel {
    private JButton boton1, boton2, boton3, boton4, boton5;

    // 1. AQUÍ EMPIEZA Y TERMINA EL CONSTRUCTOR
    public Panel_Este_Inferior() {
        
        setLayout(new GridLayout(5, 1, 0, 10));
        
        
        
        TitledBorder borde = BorderFactory.createTitledBorder("Lateral Derecho Estructuras");
        borde.setTitleJustification(TitledBorder.TOP);
        setBorder(borde);
        
        boton1=crearBotonEstructura("Granja");
        boton2=crearBotonEstructura("Herreria");
        boton3=crearBotonEstructura("Iglesia");
        boton4=crearBotonEstructura("Torre_Magica");
        boton5=crearBotonEstructura("Carpinteria");
        
        add(boton1);
        add(boton2);
        add(boton3);
        add(boton4);
        add(boton5);
        
        boton1.addActionListener(e -> pedirCantidadEstructuras("Granja"));
        boton2.addActionListener(e -> pedirCantidadEstructuras("Herreria"));
        boton3.addActionListener(e -> pedirCantidadEstructuras("Iglesia"));
        boton4.addActionListener(e -> pedirCantidadEstructuras("Torre_Magica"));
        boton5.addActionListener(e -> pedirCantidadEstructuras("Carpinteria"));
    }
    private void pedirCantidadEstructuras(String nombreTropa) {
        // Despliega la pestañita pidiendo el dato
        String respuesta = JOptionPane.showInputDialog(
            this, 
            "¿Cuántas estructuras de '" + nombreTropa + "' deseas ingresar?", 
            "Configurar Tropas", 
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Controlamos si el usuario le dio a "Cancelar" o cerró la pestañita sin escribir
        if (respuesta != null && !respuesta.trim().isEmpty()) {
            try {
                // Convertimos el texto ingresado a un número entero entero
                int cantidad = Integer.parseInt(respuesta);
                
                if (cantidad >= 0) {
                    System.out.println("-> Has asignado " + cantidad + " unidades a: " + nombreTropa);
                    // AQUÍ FUTURO: Puedes guardar 'cantidad' en las variables de tu juego.
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (NumberFormatException ex) {
                // Si pone letras o símbolos, saltará aquí en lugar de romper el programa
                JOptionPane.showMessageDialog(this, "Por favor, introduce un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // 2. AQUÍ EMPIEZA TU MÉTODO AUXILIAR (Fuera del constructor)
    private JButton crearBotonEstructura(String nombreImagen) {
        System.out.println("Cargando: " + nombreImagen + ".png");
        
        ImageIcon iconoOriginal = new ImageIcon(nombreImagen + ".png");
        
        if (iconoOriginal.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
            iconoOriginal.getImage().getWidth(null); 
        }
        
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenRedimensionada);
        
        JButton boton = new JButton(iconoFinal);
        boton.setBackground(Color.WHITE);
        
        return boton;
    } 
    
} 

class Panel_Inferior_Central extends JPanel {
    private JButton boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9;
	private Button boton11;
	private Button boton10;

    public Panel_Inferior_Central() {
        // 1. Configuración básica
        setLayout(new GridLayout(2, 5, 10, 10));
        
        TitledBorder borde = BorderFactory.createTitledBorder("Zona Inferior (Tropas)");
        borde.setTitleJustification(TitledBorder.CENTER);
        setBorder(borde);
        
        this.setPreferredSize(new Dimension(700, 250));
        
        // ========================================================
        // PASO 1: CREAR TODOS LOS BOTONES (Aquí se procesan las imágenes)
        // ========================================================
        boton1 = crearBotonTropa("Espadachin");
        boton2 = crearBotonTropa("Lancero");
        boton3 = crearBotonTropa("Ballesta");
        boton4 = crearBotonTropa("Cañon");
        boton5 = crearBotonTropa("Torre Lanza");
        boton6 = crearBotonTropa("Catapulta");
        boton7 = crearBotonTropa("Lanzacohetes");
        boton8 = crearBotonTropa("Mago");
        boton9 = crearBotonTropa("Sacerdote");
        boton10=new Button("Informe");
        boton11=new Button("Ejercito Enemigo");
        
        // ========================================================
        // PASO 2: AÑADIRLOS AL LAYOUT (Orden visual garantizado)
        // ========================================================
        // Ahora que todos existen y tienen su imagen lista en memoria,
        // los metemos a la cuadrícula. El orden aquí es sagrado.
        add(boton1); // Celda 1 (Fila 1, Col 1) -> Espadachín
        add(boton2); // Celda 2 (Fila 1, Col 2) -> Lancero
        add(boton3); // Celda 3 (Fila 1, Col 3) -> Ballesta
        add(boton4); // Celda 4 (Fila 1, Col 4) -> Cañón
        add(boton5); // Celda 5 (Fila 1, Col 5) -> Torre Lanza
        
        add(boton6); // Celda 6 (Fila 2, Col 1) -> Catapulta
        add(boton7); // Celda 7 (Fila 2, Col 2) -> Lanzacohetes
        add(boton8); // Celda 8 (Fila 2, Col 3) -> Mago
        add(boton9); // Celda 9 (Fila 2, Col 4) -> Sacerdote
        add(boton10);
        add(boton11);
     
     // PASO 2: Clics independientes con Lambdas (Mucho más limpio y sin errores de argumentos)
        boton1.addActionListener(e -> pedirCantidadTropas("Espadachin"));
        boton2.addActionListener(e -> pedirCantidadTropas("Lancero"));
        boton3.addActionListener(e -> pedirCantidadTropas("Ballesta"));
        boton4.addActionListener(e -> pedirCantidadTropas("Cañon"));
        boton5.addActionListener(e -> pedirCantidadTropas("Torre Lanza"));
        boton6.addActionListener(e -> pedirCantidadTropas("Catapulta"));
        boton7.addActionListener(e -> pedirCantidadTropas("Lanzacohetes"));
        boton8.addActionListener(e -> pedirCantidadTropas("Mago"));
        boton9.addActionListener(e -> pedirCantidadTropas("Sacerdote"));
        boton10.addActionListener(e -> System.out.println("[CLICK] Pulsaste boton10 (Debería ser Informe)"));
        boton11.addActionListener(e -> System.out.println("[CLICK] Pulsaste boton11 (Debería ser Ejercito Enemigo)"));

    }
    
    private void pedirCantidadTropas(String nombreTropa) {
        // Despliega la pestañita pidiendo el dato
        String respuesta = JOptionPane.showInputDialog(
            this, 
            "¿Cuántas unidades de '" + nombreTropa + "' deseas ingresar?", 
            "Configurar Tropas", 
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Controlamos si el usuario le dio a "Cancelar" o cerró la pestañita sin escribir
        if (respuesta != null && !respuesta.trim().isEmpty()) {
            try {
                // Convertimos el texto ingresado a un número entero entero
                int cantidad = Integer.parseInt(respuesta);
                
                if (cantidad >= 0) {
                    System.out.println("-> Has asignado " + cantidad + " unidades a: " + nombreTropa);
                    // AQUÍ FUTURO: Puedes guardar 'cantidad' en las variables de tu juego.
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (NumberFormatException ex) {
                // Si pone letras o símbolos, saltará aquí en lugar de romper el programa
                JOptionPane.showMessageDialog(this, "Por favor, introduce un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
 // 3. ¡ESTE ES EL MÉTODO QUE TE FALTA AÑADIR!
    // Debe ir dentro de la clase, pero fuera del constructor anterior.
    private JButton crearBotonTropa(String nombreImagen) {
        System.out.println("Cargando: " + nombreImagen + ".png");
        
        // Cargamos la imagen usando el nombre que le pasamos por parámetro
        ImageIcon iconoOriginal = new ImageIcon(nombreImagen + ".png");
        
        // Forzamos a Java a esperar a que la imagen se procese en memoria
        if (iconoOriginal.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
            iconoOriginal.getImage().getWidth(null); 
        }
        
        // Redimensionamos la imagen de forma limpia a 70x70 píxeles
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenRedimensionada);
        
        // Fabricamos el botón, le ponemos la imagen, el fondo blanco y lo devolvemos
        JButton boton = new JButton(iconoFinal);
        boton.setBackground(Color.WHITE);
        
        return boton; // Devolvemos el botón listo para ser asignado

    // Tu método crearBotonTropa(String nombreImagen) se queda exactamente igual abajo
}
}    