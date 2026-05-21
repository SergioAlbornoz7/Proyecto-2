package pkgmain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import pkgmain.Civilization.ResourceException;

public class UI {
	
	public static MiVentana ventana;

	public static void main(String[] args) {
		new MiVentana();
	}

}

class MiVentana extends JFrame {
	private PanelCentral panelCentral;
	private Panel_Oeste panelOeste;
	private Panel_Este panelEste;
	private BufferedImage imagen;
	
	MiVentana(){
        setBounds(800, 400, 900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Seamos Civilizados");
        init_components();
        setVisible(true);
	}
	
	public void init_components(){
		try {
			imagen = ImageIO.read(new File("Icono.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(imagen);
        panelCentral = new PanelCentral();
        add(panelCentral, BorderLayout.CENTER);
        
        panelOeste = new Panel_Oeste();
        add(panelOeste, BorderLayout.WEST);
        
        panelEste = new Panel_Este();
        add(panelEste, BorderLayout.EAST);
	}
	
	public void actuInterRecursos() {
	    panelOeste.updateRecursos();
	    
	    // AGREGA ESTA LÍNEA AQUÍ: Guarda instantáneamente los recursos restantes tras gastar
	    BaseDatos.guardarRecursos(Main.player.getFood(), Main.player.getWood(), Main.player.getIron(), Main.player.getMana());
	}
	
	public void nuevoEvento(String nombre) {
		panelCentral.registrarEvento(nombre);
		actuInterRecursos();
	}
}

class PanelCentral extends JPanel {
    private JTextArea areaRegistro; 
    private JScrollPane scrollRegistro;
    private Panel_Inferior_Central panelInferiorCentral;

    PanelCentral() {
        setLayout(new BorderLayout());
        
        areaRegistro = new JTextArea();
        areaRegistro.setBackground(Color.WHITE); 
        areaRegistro.setEditable(false);         
        areaRegistro.setLineWrap(true);          
        areaRegistro.setWrapStyleWord(true);      
        
        areaRegistro.setText("--- Bienvenido a Seamos Civilizados ---\n¡El juego ha comenzado!\n");
        
        scrollRegistro = new JScrollPane(areaRegistro);
        TitledBorder borde = BorderFactory.createTitledBorder("Registro de Eventos");
        scrollRegistro.setBorder(borde);
        
        add(scrollRegistro, BorderLayout.CENTER);
        
        panelInferiorCentral = new Panel_Inferior_Central();
        add(panelInferiorCentral, BorderLayout.SOUTH);
    }
    
    public void registrarEvento(String mensaje) {
        areaRegistro.append(mensaje + "\n");
    }
}

class Panel_Oeste extends JPanel {
	private Panel_Oeste_Inferior panelInferiorCentral;
	private Panel_Oeste_Botones panelBotonesMejora;
	
	Panel_Oeste(){
		setLayout(new BorderLayout());
		
		panelInferiorCentral = new Panel_Oeste_Inferior();
		add(panelInferiorCentral, BorderLayout.NORTH);
		
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
        ImageIcon iconoAtaque = new ImageIcon("AttackPlus.png");
        Image imagenAtaque = iconoAtaque.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoAtaquePlus = new ImageIcon(imagenAtaque);
        ImageIcon iconoDefensa = new ImageIcon("DefensePlus.png");
        Image imagenDefensa = iconoDefensa.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoDefensaPlus = new ImageIcon(imagenDefensa);

        setLayout(new GridLayout(1, 2, 5, 0));
        setPreferredSize(new Dimension(100, 100));
        
        JButton btnAtaque = new JButton(iconoAtaquePlus);
        JButton btnDefensa = new JButton(iconoDefensaPlus);
        
        btnAtaque.setBackground(Color.WHITE);
        btnDefensa.setBackground(Color.WHITE);
        
        add(btnAtaque);
        add(btnDefensa);

        // LLAMADAS DIRECTAS (Sin bloques try-catch innecesarios)
        btnAtaque.addActionListener(e -> Main.player.upgradeTechnologyAttack());
        btnDefensa.addActionListener(e -> Main.player.upgradeTechnologyDefense());
    }
}

class Panel_Este extends JPanel {
	private Panel_Este_Inferior peinferior;
	Panel_Este(){
		setLayout(new BorderLayout());
		peinferior = new Panel_Este_Inferior();
		add(peinferior, BorderLayout.NORTH);
	}
}

class Panel_Este_Inferior extends JPanel {
    private JButton boton1, boton2, boton3, boton4, boton5;

    public Panel_Este_Inferior() {
        setLayout(new GridLayout(5, 1, 0, 10));
        
        TitledBorder borde = BorderFactory.createTitledBorder("Lateral Derecho Estructuras");
        borde.setTitleJustification(TitledBorder.TOP);
        setBorder(borde);
        
        boton1 = crearBotonEstructura("Granja");
        boton2 = crearBotonEstructura("Herreria");
        boton3 = crearBotonEstructura("Iglesia");
        boton4 = crearBotonEstructura("Torre_Magica");
        boton5 = crearBotonEstructura("Carpinteria");
        
        add(boton1);
        add(boton2);
        add(boton3);
        add(boton4);
        add(boton5);
        
        // AQUÍ NO LLEVAN TRY-CATCH (No lanzan la excepción checked)
        boton1.addActionListener(e -> {
            Main.player.newFarm();
            UI.ventana.actuInterRecursos();
        });
        
        boton2.addActionListener(e -> {
            Main.player.newSmithy();
            UI.ventana.actuInterRecursos();
        });
        
        boton3.addActionListener(e -> {
            Main.player.newChurch();
            UI.ventana.actuInterRecursos();
        });
        
        boton4.addActionListener(e -> {
            Main.player.newMagicTower();
            UI.ventana.actuInterRecursos();
        });
        
        boton5.addActionListener(e -> {
            Main.player.newCarpentry();
            UI.ventana.actuInterRecursos();
        });
    } 

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
    private JButton boton10, boton11, boton12; 

    public Panel_Inferior_Central() {
        setLayout(new GridLayout(2, 5, 10, 10));
        
        TitledBorder borde = BorderFactory.createTitledBorder("Zona Inferior (Tropas)");
        borde.setTitleJustification(TitledBorder.CENTER);
        setBorder(borde);
        
        this.setPreferredSize(new Dimension(700, 250));
        
        boton1 = crearBotonTropa("Espadachin");
        boton2 = crearBotonTropa("Lancero");
        boton3 = crearBotonTropa("Ballesta");
        boton4 = crearBotonTropa("Cañon");
        boton5 = crearBotonTropa("Torre Lanza");
        boton6 = crearBotonTropa("Catapulta");
        boton7 = crearBotonTropa("Lanzacohetes");
        boton8 = crearBotonTropa("Mago");
        boton9 = crearBotonTropa("Sacerdote");
        boton10 = new JButton("Informe");
        boton11 = new JButton("Estadisticas");
        boton12 = new JButton("Ejercito Enemigo");
        
        boton10.setBackground(Color.WHITE);
        boton11.setBackground(Color.WHITE);
        
        add(boton1); 
        add(boton2); 
        add(boton3); 
        add(boton4); 
        add(boton5); 
        add(boton6); 
        add(boton7); 
        add(boton8); 
        add(boton9); 
        add(boton10);
        add(boton11);
        add(boton12);
     
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
        boton11.addActionListener(e -> Main.player.printStats());
        boton12.addActionListener(e -> Main.viewThreat());
    }
    
    private void pedirCantidadTropas(String nombreTropa) {
        String respuesta = JOptionPane.showInputDialog(
            this, 
            "¿Cuántas unidades de '" + nombreTropa + "' deseas ingresar?", 
            "Configurar Tropas", 
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (respuesta != null && !respuesta.trim().isEmpty()) {
            try {
                int cantidad = Integer.parseInt(respuesta);
                if (cantidad >= 0) {
                	if (nombreTropa == "Espadachin") {
                		Main.player.newSwordsman(cantidad);
                	} else if (nombreTropa == "Lancero") {
                		Main.player.newSpearman(cantidad);
                	} else if (nombreTropa == "Ballesta") {
                		Main.player.newCrosbow(cantidad);;
                	} else if (nombreTropa == "Cañon") {
                		Main.player.newCannon(cantidad);
                	} else if (nombreTropa == "Torre Lanza") {
                		Main.player.newArrowTower(cantidad);
                	} else if (nombreTropa == "Catapulta") {
                		Main.player.newCatapult(cantidad);
                	} else if (nombreTropa == "Lanzacohetes") {
                		Main.player.newRocketLauncher(cantidad);
                	} else if (nombreTropa == "Mago") {
                		Main.player.newMagician(cantidad);
                	} else if (nombreTropa == "Sacerdote") {
                		Main.player.newPriest(cantidad);
                	}
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private JButton crearBotonTropa(String nombreImagen) {
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