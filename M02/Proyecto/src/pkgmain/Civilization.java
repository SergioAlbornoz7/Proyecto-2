package pkgmain;

import java.util.ArrayList;

abstract class MilitaryUnit {
    final int food;
    final int wood;
    final int iron;
    final int mana;
    final int armor;
    final int attackPower;
    final int coaa; 
    final int cogw;

    public MilitaryUnit(int food, int wood, int iron, int mana, int armor, int attackPower, int coaa, int cogw) {
        this.food = food;
        this.wood = wood;
        this.iron = iron;
        this.mana = mana;
        this.armor = armor;
        this.attackPower = attackPower;
        this.coaa = coaa;
        this.cogw = cogw;
    }

	public int getFood() {
		return food;
	}

	public int getWood() {
		return wood;
	}

	public int getIron() {
		return iron;
	}

	public int getMana() {
		return mana;
	}

	public int getArmor() {
		return armor;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public int getCoaa() {
		return coaa;
	}

	public int getCogw() {
		return cogw;
	}
    
}

class Swordsman extends MilitaryUnit {
    public Swordsman() {
        super(8000, 3000, 50, 0, 400, 80, 3, 55);
    }
}

class Spearman extends MilitaryUnit {
    public Spearman() {
        super(5000, 6500, 50, 0, 1000, 150, 7, 65);
    }
}
class Crossbow extends MilitaryUnit {
    public Crossbow() {
        super(0, 45000, 7000, 0, 6000, 1000, 45, 80);
    }
}

class Cannon extends MilitaryUnit {
    public Cannon() {
        super(0, 30000, 15000, 0, 8000, 700, 70, 90);
    }
}
class ArrowTower extends MilitaryUnit {
    public ArrowTower() {
        super(0, 2000, 0, 0, 200, 80, 5, 55);
    }
}

class Catapult extends MilitaryUnit {
    public Catapult() {
        super(0, 4000, 500, 0, 1200, 250, 12, 65);
    }
}

class RocketLauncher extends MilitaryUnit {
    public RocketLauncher() {
        super(0, 50000, 5000, 0, 7000, 2000, 30, 75);
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
	public void newChurch(){
		church += 1;
	}

}