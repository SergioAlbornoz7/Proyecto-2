package pkgmain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import pkgmain.Civilization.MilitaryUnit;

public class Main {
	
	static ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[9];
	
	public static void main(String[] args) {
		
		Civilization player = new Civilization(0,0,0,0,0,0,0,0,0,0,0,0);
		
		/*Tasks*/
		TimerTask recursos= new TimerTask() {
			public void run() {
				player.setFood(player.getFood() + player.getFarm()*Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM + Variables.CIVILIZATION_FOOD_GENERATED);
				player.setIron(player.getIron() + player.getSmithy()*Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY + Variables.CIVILIZATION_IRON_GENERATED);
				player.setWood(player.getWood() + player.getCarpentry()*Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY + Variables.CIVILIZATION_WOOD_GENERATED);
				player.setMana(player.getMana() + player.getMagicTower()*Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
			}
		};
		
		TimerTask enemy_attack = new TimerTask() {

			public void run() {
				createEnemyArmy(Variables.ENEMY_FLEET_INCREASE*player.battles);
			}
		
		};
		
		/*Timer*/
		Timer timer = new Timer();
		
		/*Schedules*/
		
		timer.schedule(recursos, 0, 18000);
		
		timer.schedule(enemy_attack, 180000, 180000);
		
	}
	static void createEnemyArmy(int percentage) {
		
		/*Recursos*/
		int iron = (Variables.IRON_BASE_ENEMY_ARMY*(100+percentage))/100;
		int wood = (Variables.WOOD_BASE_ENEMY_ARMY*(100+percentage))/100;
		int food = (Variables.FOOD_BASE_ENEMY_ARMY*(100+percentage))/100;
		
		/*Create void Army*/
		
		for (int i = 0; i < 9; i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
		}
		
	}
	
}
