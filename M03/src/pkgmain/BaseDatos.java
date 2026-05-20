package pkgmain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {

	public static void main(String[] args) {
		String urlDatos = "jdbc:mysql://127.0.0.1:3307/ProyectoMixII?serverTimezone=UTC&useSSL=false";
        String usuari = "super";
        String pass = "1234";
        
        try {
            // 1) Cargar Driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("[SISTEMA] Driver cargado correctamente.");
            
            // 2) Crear conexión a la Base de Datos
            Connection conn = DriverManager.getConnection(urlDatos, usuari, pass);
            System.out.println("[SISTEMA] Conexión establecida correctamente.");
            
            // =================================================================
            // PASO 1: CARGAR PARTIDA O CREARLA DESDE CERO (Evita errores de ID)
            // =================================================================
            int idCivilizacion = 1; // ID de la partida actual
            int battlesCounterBD = 0;
            boolean partidaExiste = false;

            String queryCheck = "SELECT battles_counter FROM Civilization_stats WHERE civilization_id = ?";
            try (PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck)) {
                pstmtCheck.setInt(1, idCivilizacion);
                try (ResultSet rsCheck = pstmtCheck.executeQuery()) {
                    if (rsCheck.next()) {
                        battlesCounterBD = rsCheck.getInt("battles_counter");
                        partidaExiste = true;
                        System.out.println("[CARGA] Partida detectada en Civilization_stats. Contador: " + battlesCounterBD);
                    }
                }
            }

            // Si el ID del jugador no existe en la BD, lo registramos por primera vez
            if (!partidaExiste) {
                System.out.println("[CARGA] Partida no encontrada. Creando nueva civilización para el ID " + idCivilizacion + "...");
                String sqlNuevaPartida = "INSERT INTO Civilization_stats "
                        + "(civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, battles_counter) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmtNuevo = conn.prepareStatement(sqlNuevaPartida)) {
                    pstmtNuevo.setInt(1, idCivilizacion);
                    pstmtNuevo.setString(2, "Mi Imperio");
                    pstmtNuevo.setInt(3, 8000);  // Madera inicial
                    pstmtNuevo.setInt(4, 1000);  // Hierro inicial
                    pstmtNuevo.setInt(5, 10000); // Comida inicial
                    pstmtNuevo.setInt(6, 0);     // Maná inicial
                    pstmtNuevo.setInt(7, 0);     // 0 batallas iniciales
                    pstmtNuevo.executeUpdate();
                    System.out.println("[CARGA] ¡Nueva civilización inicializada en la BD!");
                    battlesCounterBD = 0;
                }
            }

            // =================================================================
            // PASO 2: CONTROL EXTREMO DE DUPLICADOS (Busca el máximo real en historial)
            // =================================================================
            int maxBatallaHistorial = 0;
            String queryMaxBattle = "SELECT MAX(num_battle) FROM Battle_stats WHERE civilization_id = ?";
            
            try (PreparedStatement pstmtMax = conn.prepareStatement(queryMaxBattle)) {
                pstmtMax.setInt(1, idCivilizacion);
                try (ResultSet rsMax = pstmtMax.executeQuery()) {
                    if (rsMax.next()) {
                        maxBatallaHistorial = rsMax.getInt(1);
                    }
                }
            }

            // CORREGIDO: Evaluamos el número real más alto para que NUNCA colisionen las llaves primarias
            int batallaMasAlta = Math.max(battlesCounterBD, maxBatallaHistorial);
            int numBatallaActual = batallaMasAlta + 1; 
            
            System.out.println("[JUEGO] Batalla más alta registrada en el historial: " + maxBatallaHistorial);
            System.out.println("[JUEGO] -> Preparando de forma segura la batalla número: " + numBatallaActual);
            
            // =================================================================
            // PASO 3: DATOS REALES PROVENIENTES DE TU PARTIDA/COMBATE
            // =================================================================
            // (Aquí simulas los recursos obtenidos de los escombros al acabar)
            int maderaGanada = 300;  
            int hierroGanado = 500;
            
            // =================================================================
            // PASO 4: ORDEN 1 - INSERTAR EN BATTLE_STATS (Historial)
            // =================================================================
            String insertBattleSql = "INSERT INTO Battle_stats (civilization_id, num_battle, wood_acquired, iron_acquired) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtBattle = conn.prepareStatement(insertBattleSql)) {
                pstmtBattle.setInt(1, idCivilizacion);
                pstmtBattle.setInt(2, numBatallaActual); // Usamos el número seguro calculado
                pstmtBattle.setInt(3, maderaGanada);
                pstmtBattle.setInt(4, hierroGanado);
                pstmtBattle.executeUpdate();
                System.out.println("[GUARDADO] Registro insertado con éxito en Battle_stats.");
            }

            // =================================================================
            // PASO 5: ORDEN 2 - INSERTAR EN BATTLE_LOG (Logs paso a paso)
            // =================================================================
            String insertLogSql = "INSERT INTO Battle_log (civilization_id, num_battle, num_line, log_entry) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtLog = conn.prepareStatement(insertLogSql)) {
                // Línea de log 1
                pstmtLog.setInt(1, idCivilizacion);
                pstmtLog.setInt(2, numBatallaActual);
                pstmtLog.setInt(3, 1);
                pstmtLog.setString(4, "La batalla " + numBatallaActual + " ha comenzado de manera automatizada.");
                pstmtLog.executeUpdate();
                
                // Línea de log 2
                pstmtLog.setInt(1, idCivilizacion);
                pstmtLog.setInt(2, numBatallaActual);
                pstmtLog.setInt(3, 2);
                pstmtLog.setString(4, "El ejército ha ganado. Botín recolectado.");
                pstmtLog.executeUpdate();
                System.out.println("[GUARDADO] Líneas de eventos añadidas a Battle_log.");
            }

            // =================================================================
            // PASO 6: ORDEN 3 - ACTUALIZAR EL ESTADO MAESTRO (Civilization_stats)
            // =================================================================
            // Sumamos los recursos al almacén e incrementamos el contador global de batallas de la civilización
            String updateCivSql = "UPDATE Civilization_stats SET wood_amount = wood_amount + ?, iron_amount = iron_amount + ?, battles_counter = battles_counter + 1 WHERE civilization_id = ?";
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateCivSql)) {
                pstmtUpdate.setInt(1, maderaGanada);
                pstmtUpdate.setInt(2, hierroGanado);
                pstmtUpdate.setInt(3, idCivilizacion);
                pstmtUpdate.executeUpdate();
                System.out.println("[GUARDADO] Civilization_stats actualizada de forma global.");
            }
            
            // =================================================================
            // PASO 7: MOSTRAR CONSULTA FINAL EN CONSOLA (Tu SELECT original)
            // =================================================================
            System.out.println("\n=================================================");
            System.out.println("   HISTORIAL ACTUALIZADO EN LA BASE DE DATOS    ");
            System.out.println("=================================================");
            
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
            System.out.println("=================================================\n");
            
            // 8) Cierre definitivo de la conexión
            conn.close();
            System.out.println("[SISTEMA] Conexión cerrada de forma limpia.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] El Driver de la base de datos no se ha encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERROR] Error detectado en la Base de Datos SQL: " + e.getMessage());
        }
    }

	}


