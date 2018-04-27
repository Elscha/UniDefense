package uni_defense.logic.player;

public class Player {
    
    public static final Player INSTANCE = new Player();
    
    private int gold;
    private int currentlifes;
	private int maxlifes;
	private int currentwave;
	private int endwave;
	private int enemiesKilled;
	private int enemiesAlive;
    
    private Player() {
    	this.maxlifes = 20;
    	this.currentlifes = 20;
    }

	public void updateGold(int goldChange) {
		gold = gold + goldChange;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void damage(int damage) {
		currentlifes = currentlifes - damage;
	}
	
	public void heal(int heal) {
		currentlifes = currentlifes + heal;
	}
	
	public void setCurrentlifes(int currentlifes) {
		this.currentlifes = currentlifes;
	}
	
	public void setMaxlifes(int maxlifes) {
		this.maxlifes = maxlifes;
	}
	
	public void increaseWaveByOne() {
		currentwave++;
	}
	
	public void setCurrentwave(int currentwave) {
		this.currentwave = currentwave;
	}
	
	public void setEndwave(int endwave) {
		this.endwave = endwave;
	}
	
	public void increaseEnemiesKilledByOne() {
		enemiesKilled++;
	}
	
	public void setEnemiesKilled(int enemiesKilled) {
		this.enemiesKilled = enemiesKilled;
	}
	
	public void decreaseEnemiesAliveByOne() {
		enemiesAlive--;
	}
	
	public void setEnemiesAlive(int enemiesAlive) {
		this.enemiesAlive = enemiesAlive;
	}

	public int getGold() {
		return gold;
	}

	public int getCurrentlifes() {
		return currentlifes;
	}

	public int getMaxlifes() {
		return maxlifes;
	}

	public int getCurrentwave() {
		return currentwave;
	}

	public int getEndwave() {
		return endwave;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public int getEnemiesAlive() {
		return enemiesAlive;
	}
}
