package pkgmain;

import java.util.ArrayList;

/*Clase Main*/
public class Civilization {
	
	/*tech*/
	private int technologyDefense;
	private int technologyAtack;
	
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

	public Civilization(int technologyDefense, int technologyAtack, int wood, int iron, int food, int mana,
			int magicTower, int church, int farm, int smithy, int carpentry, int battles) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
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
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAtack() {
		return technologyAtack;
	}

	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
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
	public void newChurch() throws ResourceException{
		if (food >= 10000 && wood >= 20000 && iron >= 24000 && mana >= 10000) {
			food -= 10000;
			wood -= 20000;
			iron -= 24000;
			mana -= 10000;
			church += 1;
		} else {
			throw new ResourceException("No tienes suficientes materiales para construir la iglesia");
		}
			
	}
	public void newSmithy() throws ResourceException {
        if (food >= 5000 && wood >= 10000 && iron >= 12000) {
            food -= 5000;
            wood -= 10000;
            iron -= 12000;
            smithy += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la herrería");
        }
    }

    public void newCarpentry() throws ResourceException {
        if (food >= 5000 && wood >= 10000 && iron >= 12000) {
            food -= 5000;
            wood -= 10000;
            iron -= 12000;
            carpentry += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la carpintería");
        }
    }

    public void newFarm() throws ResourceException {
        if (food >= 5000 && wood >= 10000 && iron >= 12000) {
            food -= 5000;
            wood -= 10000;
            iron -= 12000;
            farm += 1;
        } else {
            throw new ResourceException("No tienes suficientes materiales para construir la granja");
        }
    }

    public void newMagicTower() throws ResourceException {
        if (food >= 10000 && wood >= 20000 && iron >= 24000) {
            food -= 10000;
            wood -= 20000;
            iron -= 24000;
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
            this.baseDamage = Variables.BASE_DAMAGE_SWORDSMAN + (getTechnologyAtack()*Variables.PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_SWORDSMAN;
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
            this.baseDamage = Variables.BASE_DAMAGE_SPEARMAN + (getTechnologyAtack()*Variables.PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_SPEARMAN;
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
            this.baseDamage = Variables.BASE_DAMAGE_CROSSBOW + (getTechnologyAtack()*Variables.PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CROSSBOW;
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
            this.baseDamage = Variables.BASE_DAMAGE_CANNON + (getTechnologyAtack()*Variables.PLUS_ATTACK_CANNON_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CANNON;
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
            this.baseDamage = Variables.BASE_DAMAGE_ARROWTOWER + (getTechnologyAtack()*Variables.PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_ARROWTOWER;
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
            this.baseDamage = Variables.BASE_DAMAGE_CATAPULT + (getTechnologyAtack()*Variables.PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CATAPULT;
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
            this.baseDamage = Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER + (getTechnologyAtack()*Variables.PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER;
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
    		this.armor = 0;
            this.baseDamage = Variables.BASE_DAMAGE_MAGICIAN + (getTechnologyAtack()*Variables.PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_MAGICIAN;
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
    		this.armor = 0;
            this.baseDamage = 0;
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