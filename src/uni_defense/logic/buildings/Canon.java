package uni_defense.logic.buildings;

import uni_defense.logic.buildings.bullets.Fireball;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.World;

public class Canon extends ShootingBuilding {
    
    public Canon(World world) {
        super(world);
    }

    @Override
    public int getRange() {
        return 6;
    }

    @Override
    public double getCooldown() {
        return 10000;
    }

    @Override
    public void shootEnemy(int x, int y, Enemy enemy) {
        getWorld().addObject(new Fireball(getWorld(), x, y, enemy));
    }
    
}
