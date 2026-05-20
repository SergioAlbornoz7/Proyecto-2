package pkgmain;

import java.util.ArrayList;
import java.util.Arrays;

import pkgmain.Civilization.MilitaryUnit;
public class Battle {
	// Variables
	private ArrayList<MilitaryUnit>[] civilizationArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList<MilitaryUnit>[][] armies; 
	private String battleDevelopment;
	private int[][] initialCostFleet;
	private int initialNumberUnitsCivilization;
	private int initialNumberUnitsEnemy;
	private int[] wasteWoodIron;
	private int[] enemyDrops;
	private int[] civilizationDrops;
	private int[][] resourcesLooses;
	private int[][] initialArmies;
	private int actualNumberUnitsCivilization;
	private int actualNumberUnitsEnemy;
	
	//constructor
	public Battle(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
		//Ejercitos
		this.civilizationArmy = civilizationArmy;
		this.enemyArmy = enemyArmy;
		armies[0] = civilizationArmy;
		armies[1] = enemyArmy;
		initialCostFleet = new int[2][3];
		initialCostFleet[0][0] = civilizationArmy[0].size()*8000 + civilizationArmy[1].size()*5000+civilizationArmy[7].size()*12000+civilizationArmy[8].size()*15000;
		initialCostFleet[0][1] = civilizationArmy[0].size()*3000 + civilizationArmy[1].size()*6500+civilizationArmy[2].size()*45000+civilizationArmy[3].size()*30000+civilizationArmy[4].size()*2500+civilizationArmy[5].size()*4000+civilizationArmy[6].size()*50000+civilizationArmy[7].size()*2000;
		initialCostFleet[0][2] = civilizationArmy[0].size()*50 + civilizationArmy[1].size()*50+civilizationArmy[2].size()*7000+civilizationArmy[3].size()*15000+civilizationArmy[5].size()*500+civilizationArmy[6].size()*5000+civilizationArmy[7].size()*500;
		initialCostFleet[1][0] = enemyArmy[0].size()*8000 + enemyArmy[1].size()*5000;
		initialCostFleet[1][1] = enemyArmy[0].size()*3000 + enemyArmy[1].size()*6500+enemyArmy[2].size()*45000+enemyArmy[3].size()*30000+enemyArmy[4].size()*2500+enemyArmy[5].size()*4000+enemyArmy[6].size()*50000;
		initialCostFleet[1][2] = enemyArmy[0].size()*50 + enemyArmy[1].size()*50+enemyArmy[2].size()*7000+enemyArmy[3].size()*15000+enemyArmy[5].size()*500+enemyArmy[6].size()*5000;
		initialNumberUnitsCivilization = 0;
		for (ArrayList<MilitaryUnit> elemento : civilizationArmy) {
			initialNumberUnitsCivilization += elemento.size();
		}
		initialNumberUnitsEnemy = 0;
		for (ArrayList<MilitaryUnit> elemento : enemyArmy) {
			initialNumberUnitsEnemy += elemento.size();
		}
		battleDevelopment = new String("");
		actualNumberUnitsCivilization = initialNumberUnitsCivilization;
		actualNumberUnitsEnemy = initialNumberUnitsEnemy;

		
		//Recursos
		this.wasteWoodIron = new int[2];
		this.resourcesLooses = new int[2][4];
	}
	
	public void startBattle() {
		int turn = (int) (Math.random()*2+1);
		while (inBattle()) {
			if (turn == 1) {
				civilizationAttack();
				turn = 2;
			}else {
				enemyAttack();
				turn = 1;
			}
		}
	}
	
	public void civilizationAttack() {
		
	}
	
	public void enemyAttack() {
		
	}
	
	boolean inBattle() {
		
		return true;
	}
	
	//Metodos finales//
	String getBattleReport(int battles) {
		return battleDevelopment;
		}
	String getBattleDevelopment() {
		return battleDevelopment;
		}
	
	public void initInitialArmies() {
	}
	public void updateResourcesLooses() {
	}
	public void fleetResourceCost(ArrayList<MilitaryUnit> army) {}
	public void initialFleetNumber(ArrayList<MilitaryUnit> army) {}
	public int remainderPercentageFleet(ArrayList<MilitaryUnit> army) {
		return initialNumberUnitsCivilization;
		}
	public int getGroupDefender(ArrayList<MilitaryUnit> army) {
		return initialNumberUnitsCivilization;
		
	}
	public int getCivilizationGroupAttacker(){
		return initialNumberUnitsCivilization;
		}
	public int getEnemyGroupAttacker() {
		return initialNumberUnitsCivilization;
		}
	public void resetArmyArmor() {
		
	}
	
}
