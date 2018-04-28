package uni_defense.logic.buildings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BuildingModel {
	
	public static final Map<Class<? extends Building>, Integer> BUILDING_PRICES;
	
	public static final Map<Class<? extends Building>, Integer> BUILDING_RANGES;
	
	public static final Map<Class<? extends Building>, Integer> UPGRADE_PRICES;
	
	public static final Map<Class<? extends Building>, Class<? extends Building>> UPGRADE_TARGET;
	
	static {
		Map<Class<? extends Building>, Integer> tmpPrices = new HashMap<>();
		tmpPrices.put(Archer.class, 10);
		tmpPrices.put(Canon.class, 20);
		tmpPrices.put(IceTower.class, 50);
		BUILDING_PRICES = Collections.unmodifiableMap(tmpPrices);
		
		Map<Class<? extends Building>, Integer> tmpUpgrPrices = new HashMap<>();
		tmpUpgrPrices.put(Archer.class, 5);
		tmpUpgrPrices.put(Canon.class, 10);
		UPGRADE_PRICES = Collections.unmodifiableMap(tmpUpgrPrices);
		
		Map<Class<? extends Building>, Class<? extends Building>> tmpUpgrd = new HashMap<>();
		tmpUpgrd.put(Archer.class, ArcherLvl2.class);
		tmpUpgrd.put(Canon.class, CanonLvl2.class);
		UPGRADE_TARGET = Collections.unmodifiableMap(tmpUpgrd);
		
		Map<Class<? extends Building>, Integer> tmpRngs = new HashMap<>();
		tmpRngs.put(Archer.class, 5);
		tmpRngs.put(ArcherLvl2.class, 5);
		tmpRngs.put(Canon.class, 6);
		tmpRngs.put(CanonLvl2.class, 6);
		tmpRngs.put(IceTower.class, 4);
		BUILDING_RANGES = Collections.unmodifiableMap(tmpRngs);
	}

}
