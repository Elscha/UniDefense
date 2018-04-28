package uni_defense.logic.buildings;

import uni_defense.audio.Sound;
import uni_defense.logic.buildings.bullets.Fireball;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.World;

public class CanonLvl2 extends ShootingBuilding {
    
    public CanonLvl2(World world) {
        super(world);
    }

    @Override
    public int getRange() {
        return 6;
    }

    @Override
    public double getCooldown() {
        return 6000;
    }

    @Override
    public void shootEnemy(int x, int y, Enemy enemy) {
        getWorld().addObject(new Fireball(getWorld(), x, y, enemy, 15));
        new Sound("sfx/bleep3.wav").soundStart();
    }
    
    @Override
    public int getInfluenceRange() {
        return getRange();
    }
    
    @Override
    public int getThreatLevel() {
        return 7;
    }
    
}
