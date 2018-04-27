package uni_defense.logic.player;

public class Player {
    
    public static final Player INSTANCE = new Player();
    
    private int hp;
    
    private Player() {
        this.hp = 20;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public int getHp() {
        return hp;
    }
    
    public void damage(int damage) {
        hp -= damage;
    }
    
    public void heal(int healing) {
        hp += healing;
    }

}
