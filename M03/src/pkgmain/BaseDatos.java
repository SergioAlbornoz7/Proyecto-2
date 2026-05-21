package pkgmain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
    // Configuración de tu servidor local
    private static final String urlDatos = "jdbc:mysql://127.0.0.1:3307/ProyectoMixII?serverTimezone=UTC&useSSL=false";
    private static final String usuari = "super";
    private static final String pass = "1234";
    
    // Aquí se guardará el ID único generado automáticamente por la base de datos para esta sesión
    public static int currentCivilizationId = -1;

    // Asegura que el Driver se cargue una sola vez en memoria
    static {
        try {
            Class.forName("com.mysql.cj.driver.Driver");
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.err.println("[ERROR BD] No se encontró el Driver de MySQL.");
            }
        }
    }

    // Método privado para conectar de forma rápida
    private static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(urlDatos, usuari, pass);
    }

    /**
     * Inserta una nueva civilización con un nuevo ID único y crea una batalla inicial (0)
     * para que el INNER JOIN de tu web funcione inmediatamente.
     */
    public static void iniciarNuevaPartida(String nombreCivilizacion, int food, int wood, int iron, int mana) {
        String sqlCivilization = "INSERT INTO Civilization_stats (name, food_amount, wood_amount, iron_amount, mana_amount, battles_counter) VALUES (?, ?, ?, ?, ?, 0)";
        String sqlBattle = "INSERT INTO Battle_stats (civilization_id, num_battle, wood_acquired, iron_acquired) VALUES (?, 0, 0, 0)";

        try (Connection conn = obtenerConexion()) {
            // 1. Insertamos la civilización y le pedimos a MySQL que nos devuelva el ID autogenerado
            try (PreparedStatement pstmt = conn.prepareStatement(sqlCivilization, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, nombreCivilizacion);
                pstmt.setInt(2, food);
                pstmt.setInt(3, wood);
                pstmt.setInt(4, iron);
                pstmt.setInt(5, mana);
                pstmt.executeUpdate();

                // Recuperamos el ID generado
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        currentCivilizationId = generatedKeys.getInt(1);
                        System.out.println("[SISTEMA BD] ¡Nueva partida iniciada! ID Asignado: " + currentCivilizationId);
                    }
                }
            }

            // 2. Insertamos la fila inicial en Battle_stats para cumplir con el JOIN de la Web
            if (currentCivilizationId != -1) {
                try (PreparedStatement pstmtBattle = conn.prepareStatement(sqlBattle)) {
                    pstmtBattle.setInt(1, currentCivilizationId);
                    pstmtBattle.executeUpdate();
                    System.out.println("[SISTEMA BD] Fila de batalla inicial vinculada con éxito.");
                }
            }

        } catch (SQLException e) {
            System.err.println("[ERROR BD] No se pudo crear la partida en la base de datos: " + e.getMessage());
        }
    }

    /**
     * Actualiza los recursos de la partida actual en base al ID que se generó al iniciar.
     */
    public static void guardarRecursos(int food, int wood, int iron, int mana) {
        if (currentCivilizationId == -1) {
            return; // Si no hay una partida activa registrada, no hace nada
        }

        String sqlUpdate = "UPDATE Civilization_stats SET food_amount = ?, wood_amount = ?, iron_amount = ?, mana_amount = ? WHERE civilization_id = ?";
        
        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            
            pstmt.setInt(1, food);
            pstmt.setInt(2, wood);
            pstmt.setInt(3, iron);
            pstmt.setInt(4, mana);
            pstmt.setInt(5, currentCivilizationId);
            
            pstmt.executeUpdate();
            System.out.println("[SISTEMA BD] Recursos sincronizados en la Base de Datos.");
            
        } catch (SQLException e) {
            System.err.println("[ERROR BD] Error al sincronizar recursos: " + e.getMessage());
        }
    }
}

