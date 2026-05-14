package pkgmain;

import java.util.ArrayList;

import pkgmain.Civilization.MilitaryUnit;

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
	private int[] enemyDrops;
	private int[] civilizationDrops;
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
		this.initialArmies = new int[2][9];
		this.actualNumberUnitsCivilization = new int[9];
		this.actualNumberUnitsEnemy = new int[9];
		this.civilizationDrops = new int[9];
		this.enemyDrops = new int[9];
		
		
		//Recursos
		this.wasteWoodIron = new int[2];
		this.resourcesLooses = new int[2][4];
	}
}
