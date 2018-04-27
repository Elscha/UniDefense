package uni_defense.logic.enemies;

import uni_defense.logic.world.World;
import uni_defense.ui.Sprite;

public class WorkerEnemy extends Enemy {

    public WorkerEnemy(World world) {
        super(world, 0.2f);
    }

    @Override
    public Sprite getSprite() {
        return new Sprite("sprites/enemies/worker");
    }
    
}
