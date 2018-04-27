package uni_defense.logic.buildings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BuildingModel {
	
	public static final Map<Class<? extends Building>, Integer> BUILDING_PRICES;
	
	static {
		Map<Class<? extends Building>, Integer> tmpPrices = new HashMap<>();
		tmpPrices.put(Archer.class, 10);
		tmpPrices.put(Canon.class, 20);
		BUILDING_PRICES = Collections.unmodifiableMap(tmpPrices);
	}

}
