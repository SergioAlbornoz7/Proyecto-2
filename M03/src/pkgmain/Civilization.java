package pkgmain;

import java.util.ArrayList;

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
	final int armor = 0;
	final int initialArmor = 0;
	int baseDamage;
	int experience;

}

/*Subclasses de MilitaryUnit */
class Swordsman extends AttackUnit {
    public Swordsman(int armor, int baseDamage) {
        super();
        this.armor = Variables.ARMOR_SWORDSMAN + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)%Variables.ARMOR_SWORDSMAN;
        this.baseDamage = Variables.BASE_DAMAGE_SWORDSMAN + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_SWORDSMAN;
        initialArmor = this.armor;
    }
    public Swordsman() {
        super();
        armor = Variables.ARMOR_SWORDSMAN;
        baseDamage = Variables.BASE_DAMAGE_SWORDSMAN;
    }
	
	public int attack() {
		return 0;
	}
	
	public void takeDamage(int receivedDamage) {
		
	}
	
	public int getActualArmor() {
		return 0;
	}
	
	public int getFoodCost() {
		return 0;
	}
	
	public int getWoodCost() {
		return 0;
	}
	
	public int getIronCost() {
		return 0;
	}
	
	public int getManaCost() {
		return 0;
	}
	
	public int getChanceGeneratinWaste() {
		return 0;
	}
	
	public int getChanceAttackAgain() {
		return 0;
	}
	
	public void resetArmor() {
		
	}
	
	public void setExperience(int n) {
		
	}
	
	public int getExperience() {
		return 0;
	}
}

class Spearman extends AttackUnit {
    public Spearman(int armor, int baseDamage) {
        super();
        this.armor = Variables.ARMOR_SPEARMAN + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY)%Variables.ARMOR_SPEARMAN;
        this.baseDamage = Variables.BASE_DAMAGE_SPEARMAN + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_SPEARMAN;
        initialArmor = this.armor;
    }
    public Spearman() {
        super();
        armor = Variables.ARMOR_SPEARMAN;
        baseDamage = Variables.BASE_DAMAGE_SPEARMAN;
    }

	
	public int attack() {
		return 0;
	}

	
	public void takeDamage(int receivedDamage) {
		
	}

	
	public int getActualArmor() {
		return 0;
	}

	
	public int getFoodCost() {
		return 0;
	}

	
	public int getWoodCost() {
		return 0;
	}

	
	public int getIronCost() {
		return 0;
	}

	
	public int getManaCost() {
		return 0;
	}

	
	public int getChanceGeneratinWaste() {
		return 0;
	}

	
	public int getChanceAttackAgain() {
		return 0;
	}

	
	public void resetArmor() {
		
	}

	
	public void setExperience(int n) {
		
	}

	
	public int getExperience() {
		return 0;
	}
}
class Crossbow extends AttackUnit {
    public Crossbow(int armor, int baseDamage) {
        super();
        this.armor = Variables.ARMOR_CROSSBOW + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY)%Variables.ARMOR_CROSSBOW;
        this.baseDamage = Variables.BASE_DAMAGE_CROSSBOW + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CROSSBOW;
        initialArmor = this.armor;
    }
    public Crossbow() {
        super();
        armor = Variables.ARMOR_CROSSBOW;
        baseDamage = Variables.BASE_DAMAGE_CROSSBOW;
    }


	
	public int attack() {
		return 0;
	}

	
	public void takeDamage(int receivedDamage) {
		
	}

	
	public int getActualArmor() {
		return 0;
	}

	
	public int getFoodCost() {
		return 0;
	}

	
	public int getWoodCost() {
		return 0;
	}

	
	public int getIronCost() {
		return 0;
	}

	
	public int getManaCost() {
		return 0;
	}

	
	public int getChanceGeneratinWaste() {
		return 0;
	}

	
	public int getChanceAttackAgain() {
		return 0;
	}

	
	public void resetArmor() {
		
	}

	
	public void setExperience(int n) {
		
	}

	
	public int getExperience() {
		return 0;
	}
}

class Cannon extends AttackUnit {
    public Cannon(int armor, int baseDamage) {
        super();
        this.armor = Variables.ARMOR_CANNON + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_CANNON_BY_TECHNOLOGY)%Variables.ARMOR_CANNON;
        this.baseDamage = Variables.BASE_DAMAGE_CANNON + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_CANNON_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CANNON;
        initialArmor = this.armor;
    }
    public Cannon() {
        super();
        armor = Variables.ARMOR_CANNON;
        baseDamage = Variables.BASE_DAMAGE_CANNON;
    }

	
	public int attack() {
		return 0;
	}

	
	public void takeDamage(int receivedDamage) {
		
	}

	
	public int getActualArmor() {
		return 0;
	}

	
	public int getFoodCost() {
		return 0;
	}

	
	public int getWoodCost() {
		return 0;
	}

	
	public int getIronCost() {
		return 0;
	}
	public int getManaCost() {
		return 0;
	}

	
	public int getChanceGeneratinWaste() {
		return 0;
	}

	
	public int getChanceAttackAgain() {
		return 0;
	}

	
	public void resetArmor() {
		
	}

	public void setExperience(int n) {
		
	}

	public int getExperience() {
		return 0;
	}
}
class ArrowTower extends DefenseUnit {
    public ArrowTower(int armor, int baseDamage) {
        super();
		this.armor = Variables.ARMOR_ARROWTOWER + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)%Variables.ARMOR_ARROWTOWER;
        this.baseDamage = Variables.BASE_DAMAGE_ARROWTOWER + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_ARROWTOWER;
        initialArmor = this.armor;
    }
	
	public int attack() {
		return 0;
	}

	
	public void takeDamage(int receivedDamage) {
		
	}

	
	public int getActualArmor() {
		return 0;
	}

	
	public int getFoodCost() {
		return 0;
	}

	
	public int getWoodCost() {
		return 0;
	}

	
	public int getIronCost() {
		return 0;
	}
	public int getManaCost() {
		return 0;
	}

	
	public int getChanceGeneratinWaste() {
		return 0;
	}

	
	public int getChanceAttackAgain() {
		return 0;
	}

	
	public void resetArmor() {
		
	}

	public void setExperience(int n) {
		
	}

	public int getExperience() {
		return 0;
	}
}

class Catapult extends DefenseUnit {
    public Catapult(int armor, int baseDamage) {
        super();
		this.armor = Variables.ARMOR_CATAPULT + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)%Variables.ARMOR_CATAPULT;
        this.baseDamage = Variables.BASE_DAMAGE_CATAPULT + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_CATAPULT;
        initialArmor = this.armor;
    }
	
	
	public int attack() {
		return 0;
	}

	
	public void takeDamage(int receivedDamage) {
		
	}

	
	public int getActualArmor() {
		return 0;
	}

	
	public int getFoodCost() {
		return 0;
	}

	
	public int getWoodCost() {
		return 0;
	}

	
	public int getIronCost() {
		return 0;
	}
	public int getManaCost() {
		return 0;
	}

	
	public int getChanceGeneratinWaste() {
		return 0;
	}

	
	public int getChanceAttackAgain() {
		return 0;
	}

	
	public void resetArmor() {
		
	}

	public void setExperience(int n) {
		
	}

	public int getExperience() {
		return 0;
	}
}

class RocketLauncher extends DefenseUnit {
    public RocketLauncher(int armor, int baseDamage) {
        super();
		this.armor = Variables.ARMOR_ROCKETLAUNCHER + (/*Tecnologia defensa*/*Variables.PLUS_ARMOR_ROCKETLAUNCHER_BY_TECHNOLOGY)%Variables.ARMOR_ROCKETLAUNCHER;
        this.baseDamage = Variables.BASE_DAMAGE_ROCKETLAUNCHER + (/*Tecnologia ATAQUE*/*Variables.PLUS_ATTACK_ROCKETLAUNCHER_BY_TECHNOLOGY)%Variables.BASE_DAMAGE_ROCKETLAUNCHER;
        initialArmor = this.armor;
    }
	
	
	public int attack() {
		return 0;
	}

	
	public void takeDamage(int receivedDamage) {
		
	}

	
	public int getActualArmor() {
		return 0;
	}

	
	public int getFoodCost() {
		return 0;
	}

	
	public int getWoodCost() {
		return 0;
	}

	
	public int getIronCost() {
		return 0;
	}
	public int getManaCost() {
		return 0;
	}

	
	public int getChanceGeneratinWaste() {
		return 0;
	}

	
	public int getChanceAttackAgain() {
		return 0;
	}

	
	public void resetArmor() {
		
	}

	public void setExperience(int n) {
		
	}

	public int getExperience() {
		return 0;
	}
}
class Magician extends MilitaryUnit {
    public Magician() {
        super(12000, 2000, 0, 5000, 0, 3000, 75, 0);
    }
}

class Priest extends MilitaryUnit {
    public Priest() {
        super(15000, 0, 0, 15000, 0, 0, 0, 0);
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

}