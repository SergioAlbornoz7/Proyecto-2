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
import pkgmain.Civilization.ResourceException;

public class Main {
	
	static ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[4];
	
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
				Battle nb = new Battle(player.army, createEnemyArmy(Variables.ENEMY_FLEET_INCREASE*player.battles));
				nb.startBattle();
				player.battles += 1;
			}
		
		};
		
		/*Timer*/
		Timer timer = new Timer();
		
		/*Schedules*/
		
		timer.schedule(recursos, 0, 18000);
		
		timer.schedule(enemy_attack, 180000, 180000);
		
	}
	static ArrayList<MilitaryUnit>[] createEnemyArmy(int percentage) {
		
		/*Recursos*/
		int iron = (Variables.IRON_BASE_ENEMY_ARMY*(100+percentage))/100;
		int wood = (Variables.WOOD_BASE_ENEMY_ARMY*(100+percentage))/100;
		int food = (Variables.FOOD_BASE_ENEMY_ARMY*(100+percentage))/100;
		
		/*Create void Army*/
		
		for (int i = 0; i < 9; i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
		}
		while (true) {
			int rand = (int) Math.random()*100+1;
			if (rand < 36) {
				try {
					if (food >= Variables.FOOD_COST_SWORDSMAN && wood >= Variables.WOOD_COST_SWORDSMAN && iron >= Variables.IRON_COST_SWORDSMAN) {
						enemyArmy[0].add(new Civilization.Swordsman());
					} else {
						throw new ResourceException("No tienes suficientes materiales para crear mas espadachines"); 
					}
				} catch (ResourceException e) {
					try {
						if (food >= Variables.FOOD_COST_SPEARMAN && wood >= Variables.WOOD_COST_SPEARMAN && iron >= Variables.IRON_COST_SPEARMAN) {
							enemyArmy[1].add(new Civilization.Spearman());
						} else {
							throw new ResourceException("No tienes suficientes materiales para crear mas lanceros"); 
						}
					} catch (ResourceException a) {
						try {
							if (food >= Variables.FOOD_COST_CROSSBOW && wood >= Variables.WOOD_COST_CROSSBOW && iron >= Variables.IRON_COST_CROSSBOW) {
								enemyArmy[2].add(new Civilization.Crossbow());
							} else {
								throw new ResourceException("No tienes suficientes materiales para crear mas ballesteros"); 
							}
						} catch (ResourceException b) {
							try {
								if (food >= Variables.FOOD_COST_CANNON && wood >= Variables.WOOD_COST_CANNON && iron >= Variables.IRON_COST_CANNON) {
									enemyArmy[3].add(new Civilization.Cannon());
								} else {
									throw new ResourceException("No tienes suficientes materiales para crear mas cañones"); 
								}
							} catch (ResourceException c) {
								break;
							}
						}
					}
				}
			} else if (rand < 61) {
				try {
					if (food >= Variables.FOOD_COST_SPEARMAN) {
						if (food >= Variables.FOOD_COST_SPEARMAN && wood >= Variables.WOOD_COST_SPEARMAN && iron >= Variables.IRON_COST_SPEARMAN) {
							enemyArmy[1].add(new Civilization.Spearman());
						} else {
							throw new ResourceException("No tienes suficientes materiales para crear mas lanceros"); 
						}
				} catch (ResourceException e) {
					try {
						if (food >= Variables.FOOD_COST_SWORDSMAN && wood >= Variables.WOOD_COST_SWORDSMAN && iron >= Variables.IRON_COST_SWORDSMAN) {
							enemyArmy[0].add(new Civilization.Swordsman());
						} else {
							throw new ResourceException("No tienes suficientes materiales para crear mas espadachines"); 
						}
					} catch (ResourceException a) { 
						try {
							if (food >= Variables.FOOD_COST_CROSSBOW && wood >= Variables.WOOD_COST_CROSSBOW && iron >= Variables.IRON_COST_CROSSBOW) {
								enemyArmy[2].add(new Civilization.Crossbow());
							} else {
								throw new ResourceException("No tienes suficientes materiales para crear mas ballesteros"); 
							}
						} catch (ResourceException b) {
							try {
								if (food >= Variables.FOOD_COST_CANNON && wood >= Variables.WOOD_COST_CANNON && iron >= Variables.IRON_COST_CANNON) {
									enemyArmy[3].add(new Civilization.Cannon());
								} else {
									throw new ResourceException("No tienes suficientes materiales para crear mas cañones"); 
								}
							} catch (ResourceException c) {
								break;
							}
						}
					}
				}
			} else if (rand < 81) {
				try {
					if (food >= Variables.FOOD_COST_CROSSBOW && wood >= Variables.WOOD_COST_CROSSBOW && iron >= Variables.IRON_COST_CROSSBOW) {
						enemyArmy[2].add(new Civilization.Crossbow());
					} else {
						throw new ResourceException("No tienes suficientes materiales para crear mas ballesteros"); 
					}
				} catch (ResourceException e) {
					try {
						if (food >= Variables.FOOD_COST_SWORDSMAN && wood >= Variables.WOOD_COST_SWORDSMAN && iron >= Variables.IRON_COST_SWORDSMAN) {
							enemyArmy[0].add(new Civilization.Swordsman());
						} else {
							throw new ResourceException("No tienes suficientes materiales para crear mas espadachines"); 
						}
					} catch (ResourceException a) {
						try {
							if (food >= Variables.FOOD_COST_SPEARMAN && wood >= Variables.WOOD_COST_SPEARMAN && iron >= Variables.IRON_COST_SPEARMAN) {
								enemyArmy[1].add(new Civilization.Spearman());
							} else {
								throw new ResourceException("No tienes suficientes materiales para crear mas lanceros"); 
							}
						} catch (ResourceException b) {
							try {
								if (food >= Variables.FOOD_COST_CANNON && wood >= Variables.WOOD_COST_CANNON && iron >= Variables.IRON_COST_CANNON) {
									enemyArmy[3].add(new Civilization.Cannon());
								} else {
									throw new ResourceException("No tienes suficientes materiales para crear mas cañones"); 
								}
							} catch (ResourceException d) {
								break;
							}
						}
					}
				}
			} else {
				try {
					if (food >= Variables.FOOD_COST_CANNON && wood >= Variables.WOOD_COST_CANNON && iron >= Variables.IRON_COST_CANNON) {
						enemyArmy[3].add(new Civilization.Cannon());
					} else {
						throw new ResourceException("No tienes suficientes materiales para crear mas cañones"); 
					}
				} catch (ResourceException e) {
					try {
						if (food >= Variables.FOOD_COST_SWORDSMAN && wood >= Variables.WOOD_COST_SWORDSMAN && iron >= Variables.IRON_COST_SWORDSMAN) {
							enemyArmy[0].add(new Civilization.Swordsman());
						} else {
							throw new ResourceException("No tienes suficientes materiales para crear mas espadachines"); 
						}
					} catch (ResourceException a) {
						try {
							if (food >= Variables.FOOD_COST_SPEARMAN && wood >= Variables.WOOD_COST_SPEARMAN && iron >= Variables.IRON_COST_SPEARMAN) {
								enemyArmy[1].add(new Civilization.Spearman());
							} else {
								throw new ResourceException("No tienes suficientes materiales para crear mas lanceros"); 
							}
						} catch (ResourceException b) {
							try {
								if (food >= Variables.FOOD_COST_CROSSBOW && wood >= Variables.WOOD_COST_CROSSBOW && iron >= Variables.IRON_COST_CROSSBOW) {
									enemyArmy[2].add(new Civilization.Crossbow());
								} else {
									throw new ResourceException("No tienes suficientes materiales para crear mas ballesteros"); 
								}
							} catch (ResourceException c) {
								break;
							}
						}
					}
				}
			}
		}
		return enemyArmy;
	}
}
