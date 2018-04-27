package uni_defense;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public class EnemyTest {

    private static class TestEnemy extends Enemy {

        private static Point target;
        
        public TestEnemy(double speed, Point start) {
            super(new World(), speed);
            setX(start.getX());
            setY(start.getY());
        }
        
        @Override
        protected Point findNextCurrentTarget() {
            return target;
        }
        
    }
    
    @Test
    public void testMovementNegativeX() {
        TestEnemy.target = new Point(9, 10);
        TestEnemy enemy = new TestEnemy(0.2, new Point(10, 10));
        
        assertEquals(10.0, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.8, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.6, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.4, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.2, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.0, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.0, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
    }
    
    @Test
    public void testMovementPositiveX() {
        TestEnemy.target = new Point(11, 10);
        TestEnemy enemy = new TestEnemy(0.2, new Point(10, 10));
        
        assertEquals(10.0, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.2, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.4, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.6, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.8, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(11.0, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
        
        enemy.step(1000);
        assertEquals(11.0, enemy.getX(), 0.05);
        assertEquals(10.0, enemy.getY(), 0.05);
    }
    
    @Test
    public void testMovementNegativeY() {
        TestEnemy.target = new Point(10, 9);
        TestEnemy enemy = new TestEnemy(0.2, new Point(10, 10));
        
        assertEquals(10.0, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.8, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.6, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.4, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.2, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.0, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(9.0, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
    }
    
    @Test
    public void testMovementPositiveY() {
        TestEnemy.target = new Point(10, 11);
        TestEnemy enemy = new TestEnemy(0.2, new Point(10, 10));
        
        assertEquals(10.0, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.2, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.4, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.6, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(10.8, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(11.0, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
        
        enemy.step(1000);
        assertEquals(11.0, enemy.getY(), 0.05);
        assertEquals(10.0, enemy.getX(), 0.05);
    }
    
}