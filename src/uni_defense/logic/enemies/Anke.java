package uni_defense.logic.enemies;

import uni_defense.audio.Sound;
import uni_defense.logic.world.World;

public class Anke extends Enemy {

    private boolean boss;
    
    public Anke(World world) {
        super(world);
    }
    
    public Anke(World world, boolean boss) {
    	super(world);
    	this.boss = boss;
    	
    	if (boss) {
    	    initHp(); // init again, because boss is only set now
    	}
    }
    
    @Override
    protected void playDeathSound() {
        new Sound("sfx/death2.wav").soundStart(true, 15f);
    }
    
    @Override
    public double getSpeed() {
        return boss ? 0.7 : 1.0;
    }
    
    @Override
    public int getMaxHp() {
        return boss ? 250 : 55;
    }
    
    @Override
    public int getGold() {
        return boss ? 15 : 2;
    }
    
    @Override
    public int getSize() {
        return boss ? 64 : 0;
    }

}
