package uni_defense.logic.buildings;

import uni_defense.audio.Sound;
import uni_defense.logic.buildings.bullets.Bullet;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.World;

public class Archer extends ShootingBuilding {

    public Archer(World world) {
        super(world);
    }
    
    @Override
    public double getCooldown() {
        return 2000;
    }
    
    @Override
    public int getRange() {
        return 5;
    }
    
    @Override
    public void shootEnemy(int x, int y, Enemy enemy) {
        getWorld().addObject(new Bullet(getWorld(), x, y, (Enemy) enemy));
        new Sound("sfx/bleep3.wav").soundStart();
    }
    
    @Override
    public int getInfluenceRange() {
        return getRange();
    }
    
    @Override
    public int getThreatLevel() {
        return 5;
    }
    
}
