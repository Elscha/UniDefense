package uni_defense;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uni_defense.logic.world.PathFinder;
import uni_defense.logic.world.World;

public class PathFindingTest {
	
	@Test
	public void test() {
		World world = new World();
		
		PathFinder finder = new PathFinder(world);
		System.out.println(world.getSpawnPos().getX());
		System.out.println(world.getSpawnPos().getY());
		System.out.println(finder.findPath(world.getSpawnPos().getX(), world.getSpawnPos().getY()-1, world.getCastlePos().getX(), world.getCastlePos().getY()));
	}
}
