package pkgmain;

import java.util.ArrayList;

/*Clase Main*/
public class Civilization {
	
	/*tech*/
	private int technologyDefense;
	private int technologyAttack;
	
	/*resources*/
	private int wood;
	private int iron;
	private int food;
	private int mana;
	
	/*buildings*/
	private int magicTower;
	private int church;
	private int farm;
	private int smithy;
	private int carpentry;
	
	/*military*/
	int battles;
	ArrayList<MilitaryUnit>[] army = new ArrayList[9];

	public Civilization(int technologyDefense, int technologyAttack, int wood, int iron, int food, int mana,
			int magicTower, int church, int farm, int smithy, int carpentry, int battles) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAttack = technologyAttack;
		this.wood = wood;
		this.iron = iron;
		this.food = food;
		this.mana = mana;
		this.magicTower = magicTower;
		this.church = church;
		this.farm = farm;
		this.smithy = smithy;
		this.carpentry = carpentry;
		battles = 0;
        for (int i = 0; i < 9; i++) {
            army[i] = new ArrayList<MilitaryUnit>();
        }
	}
	
	/*getters and setters*/
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAttack() {
		return technologyAttack;
	}

	public void setTechnologyAttack(int technologyAttack) {
		this.technologyAttack = technologyAttack;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMagicTower() {
		return magicTower;
	}

	public void setMagicTower(int magicTower) {
		this.magicTower = magicTower;
	}

	public int getChurch() {
		return church;
	}

	public void setChurch(int church) {
		this.church = church;
	}

	public int getFarm() {
		return farm;
	}

	public void setFarm(int farm) {
		this.farm = farm;
	}

	public int getSmithy() {
		return smithy;
	}

	public void setSmithy(int smithy) {
		this.smithy = smithy;
	}

	public int getCarpentry() {
		return carpentry;
	}

	public void setCarpentry(int carpentry) {
		this.carpentry = carpentry;
	}

	public int getBattles() {
		return battles;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}

	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
	
	/*Build*/
	
	public void newChurch() throws ResourceException{
		if (food >= Variables.FOOD_COST_CHURCH && wood >= Variables.WOOD_COST_CHURCH && iron >= Variables.IRON_COST_CHURCH && mana >= 10000) {
			food -= Variables.FOOD_COST_CHURCH;
			wood -= Variables.WOOD_COST_CHURCH;
			iron -= Variables.IRON_COST_CHURCH;
			mana -= 10000;
			church += 1;
		} else {
			throw new ResourceException("No tienes suficientes materiales para construir la capilla");
		}
			
	}
	public void newSmithy() throws ResourceException {
        if (food >= Variables.FOOD_COST_SMITHY && wood >= Variables.WOOD_COST_SMITHY && iron >= Variables.IRON_COST_SMITHY) {
            food -= Variables.FOOD_COST_SMITHY;
            wood -= Variables.WOOD_COST_SMITHY;
            iron -= Variables.IRON_COST_SMITHY;
            smithy += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la herrería");
        }
    }

    public void newCarpentry() throws ResourceException {
        if (food >= Variables.FOOD_COST_CARPENTRY && wood >= Variables.WOOD_COST_CARPENTRY && iron >= Variables.IRON_COST_CARPENTRY) {
            food -= Variables.FOOD_COST_CARPENTRY;
            wood -= Variables.WOOD_COST_CARPENTRY;
            iron -= Variables.IRON_COST_CARPENTRY;
            carpentry += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la carpintería");
        }
    }

    public void newFarm() throws ResourceException {
        if (food >= Variables.FOOD_COST_FARM && wood >= Variables.WOOD_COST_FARM && iron >= Variables.IRON_COST_FARM) {
            food -= Variables.FOOD_COST_FARM;
            wood -= Variables.WOOD_COST_FARM;
            iron -= Variables.IRON_COST_FARM;
            farm += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la granja");
        }
    }

    public void newMagicTower() throws ResourceException {
        if (food >= Variables.FOOD_COST_MAGICTOWER && wood >= Variables.WOOD_COST_MAGICTOWER && iron >= Variables.IRON_COST_MAGICTOWER) {
            food -= Variables.FOOD_COST_MAGICTOWER;
            wood -= Variables.WOOD_COST_MAGICTOWER;
            iron -= Variables.IRON_COST_MAGICTOWER;
            magicTower += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la torre de magos");
        }
    }
    public void printStats() {
    	System.out.printf("%14s %n %n", "              ***************************CIVILIZATION STATS***************************              ");
    	System.out.println("--------------------------------------------------TECHNOLOGY----------------------------------------\n");
        System.out.printf("%79s %20s %n", "Attack", "Defense");
        System.out.printf("%79s %20s %n %n", getTechnologyAttack(), getTechnologyDefense());
        System.out.println("---------------------------------------------------BUILDINGS----------------------------------------\n");
        System.out.printf("%16s %20s %20s %20s %20s %n", "Farm", "Smithy", "Carpentry", "Magic Tower", "Church");
        System.out.printf("%16s %20s %20s %20s %20s %n %n", getFarm(), getSmithy(), getCarpentry(), getMagicTower(), getChurch());
        //nicio Tropas
        System.out.println("------------------------------------------------ATTACK UNITS----------------------------------------\n");
    	System.out.printf("%37s %20s %20s %20s %n", "Swordsman", "Spearman", "Crosswob", "Cannon");
    	System.out.printf("%37d %20d %20d %20d %n %n", 0, 0, 0, 0);
        System.out.println("----------------------------------------------------DEFENSES----------------------------------------\n");
        System.out.printf("%58s %20s %20s %n", "Arrow Tower", "Catapult", "Rocket Launcher");
        System.out.printf("%58d %20d %20d %n %n", 0, 0, 0);
        System.out.println("-----------------------------------------------SPECIAL UNITS----------------------------------------\n");
        System.out.printf("%79s %20s %n", "Mague", "Priest");
        System.out.printf("%79s %20s %n %n", 0, 0);
        //fin Tropas
        System.out.println("---------------------------------------------------RESOURCES----------------------------------------\n");
        System.out.printf("%37s %20s %20s %20s %n", "Food", "Wood", "Iron", "Mana");
        System.out.printf("%37d %20d %20d %20d %n", getFood(), getWood(), getIron(), getMana());
        
        System.out.printf("%37s %20s %20s %20s %n", "Food", "Wood", "Iron", "Mana");
        System.out.printf("%37d %20d %20d %20d %n", getFarm()*Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM + Variables.CIVILIZATION_FOOD_GENERATED, getCarpentry()*Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY + Variables.CIVILIZATION_WOOD_GENERATED, 
        		getSmithy()*Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY + Variables.CIVILIZATION_IRON_GENERATED, getMagicTower()*Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
    }
    	
    
    /*Upgrades*/
    
    public void upgradeTechnologyAttack() throws ResourceException {
        if (wood >= Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST + (Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST % Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST)*technologyAttack && iron >= Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST + (Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST % Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST)*technologyAttack) {
            wood -= Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST + (Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST % Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST)*technologyAttack;
            iron -= Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST+ (Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST % Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST)*technologyAttack;
            technologyAttack += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para investigar la tecnologia de ataque");
        }
    }
    
    public void upgradeTechnologyDefense() throws ResourceException {
        if (wood >= Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST + (Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST % Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST)*technologyDefense && iron >= Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST + (Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST % Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST)*technologyDefense) {
            wood -= Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST + (Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST % Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST)*technologyDefense;
            iron -= Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST+ (Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST % Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST)*technologyDefense;
            technologyDefense += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para investigar la tecnologia de defensa");
        }
    }
    
    /*Create Units*/
    
    public void newSwordsman(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_SWORDSMAN && wood >= Variables.WOOD_COST_SWORDSMAN && iron >= Variables.IRON_COST_SWORDSMAN && mana >= Variables.MANA_COST_SWORDSMAN) {
                army[0].add(new Swordsman(Variables.ARMOR_SWORDSMAN, Variables.BASE_DAMAGE_SWORDSMAN));
    			food -= Variables.FOOD_COST_SWORDSMAN;
    			wood -= Variables.WOOD_COST_SWORDSMAN;
    			iron -= Variables.IRON_COST_SWORDSMAN;
    			mana -= Variables.MANA_COST_SWORDSMAN;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas espadachines"); 
            }
    	}
    }
    public void newSpearman(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_SPEARMAN && wood >= Variables.WOOD_COST_SPEARMAN && iron >= Variables.IRON_COST_SPEARMAN && mana >= Variables.MANA_COST_SPEARMAN) {
                army[1].add(new Spearman(Variables.ARMOR_SPEARMAN, Variables.BASE_DAMAGE_SPEARMAN));
    			food -= Variables.FOOD_COST_SPEARMAN;
    			wood -= Variables.WOOD_COST_SPEARMAN;
    			iron -= Variables.IRON_COST_SPEARMAN;
    			mana -= Variables.MANA_COST_SPEARMAN;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas lanceros"); 
            }
    	}
    }
    public void newCrosbow(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_CROSSBOW && wood >= Variables.WOOD_COST_CROSSBOW && iron >= Variables.IRON_COST_CROSSBOW && mana >= Variables.MANA_COST_CROSSBOW) {
                army[2].add(new Crossbow(Variables.ARMOR_CROSSBOW, Variables.BASE_DAMAGE_CROSSBOW));
    			food -= Variables.FOOD_COST_CROSSBOW;
    			wood -= Variables.WOOD_COST_CROSSBOW;
    			iron -= Variables.IRON_COST_CROSSBOW;
    			mana -= Variables.MANA_COST_CROSSBOW;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas ballesteros"); 
            }
    	}
    }
    public void newCannon(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_CANNON && wood >= Variables.WOOD_COST_CANNON && iron >= Variables.IRON_COST_CANNON && mana >= Variables.MANA_COST_CANNON) {
                army[3].add(new Cannon(Variables.ARMOR_CANNON, Variables.BASE_DAMAGE_CANNON));
    			food -= Variables.FOOD_COST_CANNON;
    			wood -= Variables.WOOD_COST_CANNON;
    			iron -= Variables.IRON_COST_CANNON;
    			mana -= Variables.MANA_COST_CANNON;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas cañones"); 
            }
    	}
    }
    public void newArrowTower(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_ARROWTOWER && wood >= Variables.WOOD_COST_ARROWTOWER && iron >= Variables.IRON_COST_ARROWTOWER && mana >= Variables.MANA_COST_ARROWTOWER) {
                army[4].add(new ArrowTower(Variables.ARMOR_ARROWTOWER, Variables.BASE_DAMAGE_ARROWTOWER));
    			food -= Variables.FOOD_COST_ARROWTOWER;
    			wood -= Variables.WOOD_COST_ARROWTOWER;
    			iron -= Variables.IRON_COST_ARROWTOWER;
    			mana -= Variables.MANA_COST_ARROWTOWER;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas Torres"); 
            }
    	}
    }
    public void newCatapult(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_CATAPULT && wood >= Variables.WOOD_COST_CATAPULT && iron >= Variables.IRON_COST_CATAPULT && mana >= Variables.MANA_COST_CATAPULT) {
                army[4].add(new Catapult(Variables.ARMOR_CATAPULT, Variables.BASE_DAMAGE_CATAPULT));
    			food -= Variables.FOOD_COST_CATAPULT;
    			wood -= Variables.WOOD_COST_CATAPULT;
    			iron -= Variables.IRON_COST_CATAPULT;
    			mana -= Variables.MANA_COST_CATAPULT;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas Catapultas"); 
            }
    	}
    }
    public void newRocketLauncher(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_ROCKETLAUNCHERTOWER && wood >= Variables.WOOD_COST_ROCKETLAUNCHERTOWER && iron >= Variables.IRON_COST_ROCKETLAUNCHERTOWER && mana >= Variables.MANA_COST_ROCKETLAUNCHERTOWER) {
                army[3].add(new RocketLauncher(Variables.ARMOR_ROCKETLAUNCHERTOWER, Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER));
    			food -= Variables.FOOD_COST_ROCKETLAUNCHERTOWER;
    			wood -= Variables.WOOD_COST_ROCKETLAUNCHERTOWER;
    			iron -= Variables.IRON_COST_ROCKETLAUNCHERTOWER;
    			mana -= Variables.MANA_COST_ROCKETLAUNCHERTOWER;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas Lanzacohetes"); 
            }
    	}
    }
    public void newMagician(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_MAGICIAN && wood >= Variables.WOOD_COST_MAGICIAN && iron >= Variables.IRON_COST_MAGICIAN && mana >= Variables.MANA_COST_MAGICIAN) {
                army[3].add(new Magician(0, Variables.BASE_DAMAGE_MAGICIAN));
    			food -= Variables.FOOD_COST_MAGICIAN;
    			wood -= Variables.WOOD_COST_MAGICIAN;
    			iron -= Variables.IRON_COST_MAGICIAN;
    			mana -= Variables.MANA_COST_MAGICIAN;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas Mago"); 
            }
    	}
    }
    public void newPriest(int n) throws ResourceException {
    	for (int i = n; i != 0; i--) {
    		if (food >= Variables.FOOD_COST_PRIEST && wood >= Variables.WOOD_COST_PRIEST && iron >= Variables.IRON_COST_PRIEST && mana >= Variables.MANA_COST_PRIEST) {
                army[3].add(new Priest(0, 0));
    			food -= Variables.FOOD_COST_PRIEST;
    			wood -= Variables.WOOD_COST_PRIEST;
    			iron -= Variables.IRON_COST_PRIEST;
    			mana -= Variables.MANA_COST_PRIEST;
            } else {
            	System.out.println(String.format("Se han creado %d espadachines", n-i));
                throw new ResourceException("No tienes suficientes materiales para crear mas Clerigo"); 
            }
    	}
    }
    /*Interfaces*/
    
    interface MilitaryUnit {
    	abstract int attack();
    	abstract void takeDamage(int receivedDamage);
    	abstract int getActualArmor();
    	abstract int getFoodCost();
    	abstract int getWoodCost();
    	abstract int getIronCost();
    	abstract int getManaCost();
    	abstract int getChanceGeneratinWaste();
    	abstract int getChanceAttackAgain();
    	abstract void resetArmor();
    	abstract void setExperience(int n);
    	abstract int getExperience();
    }

    /*Military Classes*/
    abstract class AttackUnit implements MilitaryUnit, Variables {
    	int armor;
    	int initialArmor;
    	int baseDamage;
    	int experience;
    	boolean sanctified;
    }

    abstract class DefenseUnit implements MilitaryUnit, Variables {
    	int armor;
    	int initialArmor;
    	int baseDamage;
    	int experience;
    	boolean sanctified;
    }

    abstract class SpecialUnit implements MilitaryUnit, Variables {
    	int armor = 0;
    	int initialArmor = 0;
    	int baseDamage;
    	int experience;
    }

    /*Subclasses de MilitaryUnit */
    /*Attack*/
    class Swordsman extends AttackUnit {
        public Swordsman(int armor, int baseDamage) {
            super();
            this.armor = Variables.ARMOR_SWORDSMAN + (getTechnologyDefense()*Variables.PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)%Variables.ARMOR_SWORDSMAN;
            this.baseDamage = Variables.BASE_DAMAGE_SWORDSMAN + (getTechnologyAttack()*Variables.PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_SWORDSMAN;
            initialArmor = this.armor;
    		
        }
        public Swordsman() {
            super();
            armor = Variables.ARMOR_SWORDSMAN;
            baseDamage = Variables.BASE_DAMAGE_SWORDSMAN;
    		initialArmor = this.armor;
        }

        
        public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_SWORDSMAN;
        }

        
        public int getWoodCost() {
            return WOOD_COST_SWORDSMAN;
        }

        
        public int getIronCost() {
            return IRON_COST_SWORDSMAN;
        }

        
        public int getManaCost() {
            return MANA_COST_SWORDSMAN;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_SWORDSMAN;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_SWORDSMAN;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    class Spearman extends AttackUnit {
        public Spearman(int armor, int baseDamage) {
            super();
            this.armor = Variables.ARMOR_SPEARMAN + (getTechnologyDefense()*Variables.PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY)%Variables.ARMOR_SPEARMAN;
            this.baseDamage = Variables.BASE_DAMAGE_SPEARMAN + (getTechnologyAttack()*Variables.PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_SPEARMAN;
            initialArmor = this.armor;
        }
        public Spearman() {
            super();
            armor = Variables.ARMOR_SPEARMAN;
            baseDamage = Variables.BASE_DAMAGE_SPEARMAN;
    		initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_SPEARMAN;
        }

        
        public int getWoodCost() {
            return WOOD_COST_SPEARMAN;
        }

        
        public int getIronCost() {
            return IRON_COST_SPEARMAN;
        }

        
        public int getManaCost() {
            return MANA_COST_SPEARMAN;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_SPEARMAN;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_SPEARMAN;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    class Crossbow extends AttackUnit {
        public Crossbow(int armor, int baseDamage) {
            super();
            this.armor = Variables.ARMOR_CROSSBOW + (getTechnologyDefense()*Variables.PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY)%Variables.ARMOR_CROSSBOW;
            this.baseDamage = Variables.BASE_DAMAGE_CROSSBOW + (getTechnologyAttack()*Variables.PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CROSSBOW;
            initialArmor = this.armor;
        }
        public Crossbow() {
            super();
            armor = Variables.ARMOR_CROSSBOW;
            baseDamage = Variables.BASE_DAMAGE_CROSSBOW;
    		initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_CROSSBOW;
        }

        
        public int getWoodCost() {
            return WOOD_COST_CROSSBOW;
        }

        
        public int getIronCost() {
            return IRON_COST_CROSSBOW;
        }

        
        public int getManaCost() {
            return MANA_COST_CROSSBOW;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_CROSSBOW;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_CROSSBOW;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    class Cannon extends AttackUnit {
        public Cannon(int armor, int baseDamage) {
            super();
            this.armor = Variables.ARMOR_CANNON + (getTechnologyDefense()*Variables.PLUS_ARMOR_CANNON_BY_TECHNOLOGY)%Variables.ARMOR_CANNON;
            this.baseDamage = Variables.BASE_DAMAGE_CANNON + (getTechnologyAttack()*Variables.PLUS_ATTACK_CANNON_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CANNON;
            initialArmor = this.armor;
        }
        public Cannon() {
            super();
            armor = Variables.ARMOR_CANNON;
            baseDamage = Variables.BASE_DAMAGE_CANNON;
    		initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_CANNON;
        }

        
        public int getWoodCost() {
            return WOOD_COST_CANNON;
        }

        
        public int getIronCost() {
            return IRON_COST_CANNON;
        }

        
        public int getManaCost() {
            return MANA_COST_CANNON;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_CANNON;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_CANNON;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    /*Defense*/
    class ArrowTower extends DefenseUnit {
        public ArrowTower(int armor, int baseDamage) {
            super();
    		this.armor = Variables.ARMOR_ARROWTOWER + (getTechnologyDefense()*Variables.PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)%Variables.ARMOR_ARROWTOWER;
            this.baseDamage = Variables.BASE_DAMAGE_ARROWTOWER + (getTechnologyAttack()*Variables.PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_ARROWTOWER;
            initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_ARROWTOWER;
        }

        
        public int getWoodCost() {
            return WOOD_COST_ARROWTOWER;
        }

        
        public int getIronCost() {
            return IRON_COST_ARROWTOWER;
        }

        
        public int getManaCost() {
            return MANA_COST_ARROWTOWER;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_ARROWTOWER;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_ARROWTOWER;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }

    class Catapult extends DefenseUnit {
        public Catapult(int armor, int baseDamage) {
            super();
    		this.armor = Variables.ARMOR_CATAPULT + (getTechnologyDefense()*Variables.PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)%Variables.ARMOR_CATAPULT;
            this.baseDamage = Variables.BASE_DAMAGE_CATAPULT + (getTechnologyAttack()*Variables.PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CATAPULT;
            initialArmor = this.armor;
        }
    		public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_CATAPULT;
        }

        
        public int getWoodCost() {
            return WOOD_COST_CATAPULT;
        }

        
        public int getIronCost() {
            return IRON_COST_CATAPULT;
        }

        
        public int getManaCost() {
            return MANA_COST_CATAPULT;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_CATAPULT;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_CATAPULT;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    class RocketLauncher extends DefenseUnit {
        public RocketLauncher(int armor, int baseDamage) {
            super();
    		this.armor = Variables.ARMOR_ROCKETLAUNCHERTOWER + (getTechnologyDefense()*Variables.PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)%Variables.ARMOR_ROCKETLAUNCHERTOWER;
            this.baseDamage = Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER + (getTechnologyAttack()*Variables.PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER;
            initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_ROCKETLAUNCHERTOWER;
        }

        
        public int getWoodCost() {
            return WOOD_COST_ROCKETLAUNCHERTOWER;
        }

        
        public int getIronCost() {
            return IRON_COST_ROCKETLAUNCHERTOWER;
        }

        
        public int getManaCost() {
            return MANA_COST_ROCKETLAUNCHERTOWER;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    /*Special*/
    class Magician extends SpecialUnit {
        public Magician(int armor, int baseDamage) {
            super();
    		this.armor = armor;
            this.baseDamage = Variables.BASE_DAMAGE_MAGICIAN + (getTechnologyAttack()*Variables.PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_MAGICIAN;
    		initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_MAGICIAN;
        }

        
        public int getWoodCost() {
            return WOOD_COST_MAGICIAN;
        }

        
        public int getIronCost() {
            return IRON_COST_MAGICIAN;
        }

        
        public int getManaCost() {
            return MANA_COST_MAGICIAN;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_MAGICIAN;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_MAGICIAN;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }

    class Priest extends SpecialUnit {
        public Priest(int armor, int baseDamage) {
    		this.armor = armor;
            this.baseDamage = baseDamage;
    		initialArmor = this.armor;
        }
    	public int attack() {
            return baseDamage;
        }

        
        public void takeDamage(int receivedDamage) {
            
        }

        
        public int getActualArmor() {
            return armor;
        }

        
        public int getFoodCost() {
            return FOOD_COST_PRIEST;
        }

        
        public int getWoodCost() {
            return WOOD_COST_PRIEST;
        }

        
        public int getIronCost() {
            return IRON_COST_PRIEST;
        }

        
        public int getManaCost() {
            return MANA_COST_PRIEST;
        }

        
        public int getChanceGeneratinWaste() {
            return CHANCE_GENERATNG_WASTE_PRIEST;
        }

        
        public int getChanceAttackAgain() {
            return CHANCE_ATTACK_AGAIN_PRIEST;
        }

        
        public void resetArmor() {
            armor = initialArmor;
        }

        
        public void setExperience(int n) {
            experience = n;
        }

        
        public int getExperience() {
            return experience;
        }
    }
    /*Excepciones*/
    class ResourceException extends Exception {
    	public ResourceException(String s){
    		super(s);
    	}
    }
    class BuildingException extends Exception {
    	public BuildingException(String s){
    		super(s);
    	}
    }
}