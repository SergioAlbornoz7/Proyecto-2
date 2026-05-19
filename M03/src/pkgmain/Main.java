package pkgmain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
    	// Cambia tu línea actual por esta:
    	String urlDatos = "jdbc:mysql://127.0.0.1:3307/ProyectoMixII?serverTimezone=UTC&useSSL=false";        String usuari = "super";
        String pass = "1234";
        
        try {
            // 1) Cargar Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            // 2) Crear conexión
            Connection conn = DriverManager.getConnection(urlDatos, usuari, pass);
            System.out.println("Connection carregat correctament");
            
         // =================================================================
         // PASO 1: CARGAR PARTIDA O CREARLA, Y CALCULAR EL NÚMERO DE BATALLA REAL
         // =================================================================
         int idCivilizacion = 1;
         int battlesCounterBD = 0;
         boolean partidaExiste = false;

         // 1. Primero comprobamos si existe la civilización
         String queryCheck = "SELECT battles_counter FROM Civilization_stats WHERE civilization_id = ?";
         try (PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck)) {
             pstmtCheck.setInt(1, idCivilizacion);
             try (ResultSet rsCheck = pstmtCheck.executeQuery()) {
                 if (rsCheck.next()) {
                     battlesCounterBD = rsCheck.getInt("battles_counter");
                     partidaExiste = true;
                     System.out.println("Partida detectada en Civilization_stats. Contador actual: " + battlesCounterBD);
                 }
             }
         }

         // 2. Si no existe, la creamos desde cero
         if (!partidaExiste) {
             System.out.println("No se encontró la partida. Creando nueva civilización...");
             String sqlNuevaPartida = "INSERT INTO Civilization_stats (civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, battles_counter) VALUES (?, ?, ?, ?, ?, ?, ?)";
             try (PreparedStatement pstmtNuevo = conn.prepareStatement(sqlNuevaPartida)) {
                 pstmtNuevo.setInt(1, idCivilizacion);
                 pstmtNuevo.setString(2, "Mi Imperio");
                 pstmtNuevo.setInt(3, 8000);  
                 pstmtNuevo.setInt(4, 1000);  
                 pstmtNuevo.setInt(5, 10000); 
                 pstmtNuevo.setInt(6, 0);     
                 pstmtNuevo.setInt(7, 0);     
                 pstmtNuevo.executeUpdate();
                 battlesCounterBD = 0;
             }
         }

         // =================================================================
         // SOLUCIÓN AL ERROR: BUSCAR EL MAXIMO REAL EN EL HISTORIAL
         // =================================================================
         int maxBatallaHistorial = 0;
         String queryMaxBattle = "SELECT MAX(num_battle) FROM Battle_stats WHERE civilization_id = ?";

         try (PreparedStatement pstmtMax = conn.prepareStatement(queryMaxBattle)) {
             pstmtMax.setInt(1, idCivilizacion);
             try (ResultSet rsMax = pstmtMax.executeQuery()) {
                 if (rsMax.next()) {
                     maxBatallaHistorial = rsMax.getInt(1); // Obtiene el número más alto en el historial
                 }
             }
         }

         // Evaluamos cuál es el número mayor real para evitar colisiones
         // Si el historial tiene una batalla 3, pero Civilization_stats decía 2, usamos el del historial (3) y le sumamos 1.
         int batallaMasAlta = Math.max(battlesCounterBD, maxBatallaHistorial);
         int numBatallaActual = batallaMasAlta + 1; 

         System.out.println("-> Batalla más alta detectada en registros: " + batallaMasAlta);
         System.out.println("-> Preparando de forma segura la batalla número: " + numBatallaActual);
            
            // =================================================================
            // PASO 2: LÓGICA DEL JUEGO (Aquí calculas lo que ganas)
            // =================================================================
            // Esto vendría de tus clases 'Battle' o 'Civilization'. Ponemos valores de prueba:
            int maderaGanada = 450;  
            int hierroGanado = 150;
            
            // =================================================================
            // PASO 3: ORDEN 1 - INSERTAR EN BATTLE_STATS (Historial)
            // =================================================================
            String insertBattleSql = "INSERT INTO Battle_stats (civilization_id, num_battle, wood_acquired, iron_acquired) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtBattle = conn.prepareStatement(insertBattleSql)) {
                pstmtBattle.setInt(1, idCivilizacion);
                pstmtBattle.setInt(2, numBatallaActual); // Usa el número correlativo real
                pstmtBattle.setInt(3, maderaGanada);
                pstmtBattle.setInt(4, hierroGanado);
                pstmtBattle.executeUpdate();
                System.out.println("Historial de Battle_stats guardado para la batalla " + numBatallaActual);
            }

            // =================================================================
            // PASO 4: ORDEN 2 - INSERTAR EN BATTLE_LOG (Detalle de eventos)
            // =================================================================
            String insertLogSql = "INSERT INTO Battle_log (civilization_id, num_battle, num_line, log_entry) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtLog = conn.prepareStatement(insertLogSql)) {
                pstmtLog.setInt(1, idCivilizacion);
                pstmtLog.setInt(2, numBatallaActual);
                pstmtLog.setInt(3, 1);
                pstmtLog.setString(4, "La batalla " + numBatallaActual + " ha comenzado con datos reales de la BD.");
                pstmtLog.executeUpdate();
                
                pstmtLog.setInt(1, idCivilizacion);
                pstmtLog.setInt(2, numBatallaActual);
                pstmtLog.setInt(3, 2);
                pstmtLog.setString(4, "Fin del enfrentamiento. El enemigo ha sido repelido.");
                pstmtLog.executeUpdate();
                System.out.println("Líneas de Battle_log insertadas.");
            }

            // =================================================================
            // PASO 5: ORDEN 3 - ACTUALIZAR CIVILIZATION_STATS (Estado Maestro)
            // =================================================================
            // Incrementamos los almacenes sumando lo ganado y sumamos 1 al contador global de batallas
            String updateCivSql = "UPDATE Civilization_stats SET wood_amount = wood_amount + ?, iron_amount = iron_amount + ?, battles_counter = battles_counter + 1 WHERE civilization_id = ?";
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateCivSql)) {
                pstmtUpdate.setInt(1, maderaGanada);
                pstmtUpdate.setInt(2, hierroGanado);
                pstmtUpdate.setInt(3, idCivilizacion);
                pstmtUpdate.executeUpdate();
                System.out.println("Civilization_stats actualizada (Contador de batallas ahora es: " + numBatallaActual + ")");
            }
            
            // =================================================================
            // PASO 6: MOSTRAR CONSULTA FINAL (Para verificar en consola)
            // =================================================================
            System.out.println("\n--- HISTORIAL ACTUALIZADO EN LA BASE DE DATOS ---");
            String querySql = "SELECT num_battle, civilization_id, wood_acquired, iron_acquired FROM Battle_stats WHERE civilization_id = ?";
            try (PreparedStatement stmnt = conn.prepareStatement(querySql)) {
                stmnt.setInt(1, idCivilizacion);
                try (ResultSet rs = stmnt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("num_battle = " + rs.getInt("num_battle") + 
                                           " | civilization_id = " + rs.getInt("civilization_id") + 
                                           " | wood_acquired = " + rs.getInt("wood_acquired") + 
                                           " | iron_acquired = " + rs.getInt("iron_acquired"));
                    }
                }
            }
            
            conn.close(); // Cerramos la conexión de forma segura
            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no ha cargado correctamente");
        } catch (SQLException e) {
            System.out.println("Error en la Base de Datos: " + e.getMessage());
        }
    }
}
