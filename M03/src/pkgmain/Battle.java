package pkgmain;

import java.util.ArrayList;

public class Battle {
	// Variables
	private ArrayList<MilitaryUnit> civilizationArmy;
	private ArrayList<MilitaryUnit> enemyArmy;
	private ArrayList<MilitaryUnit>[][] armies; 
	private String battleDevelopment;
	private int[][] initialCostFleet;
	private int initialNumberUnitsCivilization;
	private int initialNumbersUnitsEnemy;
	private int[] wasteWoodIron;
	private int enemyDrops;
	private int civilizationDrops;
	private int[][] resourcesLooses;
	private int[][] initialArmies;
	private int[] actualNumberUnitsCivilization;
	private int[] actualNumberUnitsEnemy;
	
	//constructor
	public Battle(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {
		//Ejercitos
		this.civilizationArmy = civilizationArmy;
		this.enemyArmy = enemyArmy;
		this.initialCostFleet = new int[2][3];
		
		//Recursos
		this.wasteWoodIron = new int[2];
	}
}
