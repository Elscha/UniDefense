package uni_defense.logic.buildings;

import uni_defense.audio.Sound;
import uni_defense.logic.buildings.bullets.Bullet;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.World;

public class ArcherLvl2 extends ShootingBuilding {

    public ArcherLvl2(World world) {
        super(world);
    }
    
    @Override
    public double getCooldown() {
        return 1500;
    }
    
    @Override
    public int getRange() {
        return 5;
    }
    
    @Override
    public void shootEnemy(int x, int y, Enemy enemy) {
        getWorld().addObject(new Bullet(getWorld(), x, y, (Enemy) enemy, 8));
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
