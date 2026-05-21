package pkgmain;

import java.util.ArrayList;
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
		armies = new ArrayList[2][];
		armies[0] = civilizationArmy;
		armies[1] = enemyArmy;
		battleDevelopment = new String("");
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
		initialArmies = new int [2][9];
		int id = 0;
		for (ArrayList<MilitaryUnit> elemento : civilizationArmy) {
			initialArmies[0][id] = elemento.size();
			id++;
		}
		id = 0;
		for (ArrayList<MilitaryUnit> elemento : enemyArmy) {
			initialArmies[1][id] = elemento.size();
			id++;
		}
		actualNumberUnitsCivilization = initialNumberUnitsCivilization;
		actualNumberUnitsEnemy = initialNumberUnitsEnemy;

		
		//Recursos
		this.wasteWoodIron = new int[2];
		this.resourcesLooses = new int[2][3];
		
	}
	
	public void startBattle() {
        int turn = (byte) (Math.random()*2+1);
        while ((actualNumberUnitsCivilization >  ((initialNumberUnitsCivilization/100)*20) || actualNumberUnitsEnemy > ((initialNumberUnitsEnemy/100)*20)) && (actualNumberUnitsEnemy > 0 && actualNumberUnitsCivilization > 0)) {
            if (turn == 1) {

                /*offense*/
                int grp = (int) (Math.random() * 100);
                int group = -1;
                int acumulado = 0;

                for (int i = 0; i <= 8; i++) {
                    acumulado += Variables.CHANCE_ATTACK_CIVILIZATION_UNITS[i];
                    if (grp < acumulado) {
                        group = i;
                        break;
                    }
                }
                
                if (civilizationArmy[group].isEmpty()) {
                    continue;
                }
                
                MilitaryUnit offensive = civilizationArmy[group].get((int)(Math.random() * civilizationArmy[group].size())) ;
                
                /*Defense*/

                int total = actualNumberUnitsEnemy;
                int[] prov = new int[5];
                for (int i = 0; i < 5; i++) {
                    if (total > 0) {
                        prov[i] = (int) (((double) enemyArmy[i].size() / total) * 100);
                    }
                }
                grp = (int) (Math.random() * 100);
                group = -1;
                acumulado = 0;

                for (int i = 0; i <= 4; i++) {
                    acumulado += prov[i];
                    if (grp < acumulado) {
                        group = i;
                        break;
                    }
                }
                
                if (group == -1 || enemyArmy[group].isEmpty()) {
                    for (int i = 0; i < 5; i++) {
                        if (!enemyArmy[i].isEmpty()) {
                            group = i;
                            break;
                        }
                    }
                }
                
                int u = (int)(Math.random() * enemyArmy[group].size());
                MilitaryUnit defensive = enemyArmy[group].get(u) ;

                /*battle*/
                defensive.takeDamage(offensive.attack());
                int aa = (int)(Math.random()*100);
                if (aa <= offensive.getChanceAttackAgain() && defensive.getActualArmor()> 0) {
                    defensive.takeDamage(offensive.attack());
                }
                if (defensive.getActualArmor() <= 0) {
                    int wc = (int)(Math.random()*100);
                    if (wc <= defensive.getChanceGeneratinWaste()){
                        wasteWoodIron[0] = defensive.getWoodCost();
                        wasteWoodIron[1] = defensive.getIronCost();
                    }
                    resourcesLooses[1][0] += defensive.getFoodCost();
                    resourcesLooses[1][1] += defensive.getWoodCost();
                    resourcesLooses[1][2] += defensive.getIronCost();
                    enemyArmy[group].remove(u);
                    actualNumberUnitsEnemy -= 1;
                }
                /*log del ataque*/ 
                
                turn = 2;
            }else {
                    /*offense*/
                int grp = (int) (Math.random() * 100);
                int group = -1;
                int acumulado = 0;

                for (int i = 0; i <= 4; i++) {
                    acumulado += Variables.CHANCE_ATTACK_ENEMY_UNITS[i];
                    if (grp < acumulado) {
                        group = i;
                        break;
                    }
                }
                
                if (enemyArmy[group].isEmpty()) {
                    continue;
                }
                
                MilitaryUnit offensive = enemyArmy[group].get((int)(Math.random() * enemyArmy[group].size())) ;
                
                /*Defense*/

                int total = actualNumberUnitsCivilization;
                int[] prov = new int[9];
                for (int i = 0; i < 9; i++) {
                    if (total > 0) {
                        prov[i] = (int) (((double) civilizationArmy[i].size() / total) * 100);
                    }
                }
                grp = (int) (Math.random() * 100);
                group = -1;
                acumulado = 0;

                for (int i = 0; i <= 8; i++) {
                    acumulado += prov[i];
                    if (grp < acumulado) {
                        group = i;
                        break;
                    }
                }
                
                if (group == -1 || civilizationArmy[group].isEmpty()) {
                    for (int i = 0; i < 9; i++) {
                        if (!civilizationArmy[i].isEmpty()) {
                            group = i;
                            break;
                        }
                    }
                }
                
                int u = (int)(Math.random() * civilizationArmy[group].size());
                MilitaryUnit defensive = civilizationArmy[group].get(u) ;

                /*battle*/
                defensive.takeDamage(offensive.attack());
                int aa = (int)(Math.random()*100);
                if (aa <= offensive.getChanceAttackAgain() && defensive.getActualArmor()> 0) {
                    defensive.takeDamage(offensive.attack());
                }
                if (defensive.getActualArmor() <= 0) {
                    int wc = (int)(Math.random()*100);
                    if (wc <= defensive.getChanceGeneratinWaste()){
                        wasteWoodIron[0] = defensive.getWoodCost();
                        wasteWoodIron[1] = defensive.getIronCost();
                    }
                    resourcesLooses[0][0] += defensive.getFoodCost();
                    resourcesLooses[0][1] += defensive.getWoodCost();
                    resourcesLooses[0][2] += defensive.getIronCost();
                    civilizationArmy[group].remove(u);
                    actualNumberUnitsCivilization -= 1;
                }
                /*log del ataque*/ 
                turn = 1;
            }
        }
        if (resourcesLooses[0][0]+ (resourcesLooses[0][1]*5) +(resourcesLooses[0][2]*10) > resourcesLooses[1][0]+ (resourcesLooses[1][1]*5) +(resourcesLooses[1][2]*10)) {
            /*log Victoria*/
			Main.player.setWood(Main.player.getWood()+ wasteWoodIron[0]);
			Main.player.setIron(Main.player.getIron()+ wasteWoodIron[1]);
        } else {
			/*logDerrota*/
		}
    }
}

