package net.termilu.termc.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.termilu.termc.TerMc;
import net.termilu.termc.item.ModItems;

public class Fueltems {

    public static void registerFuelItems() {
        TerMc.LOGGER.info("Registering Fuel Items for: " + TerMc.MOD_ID);
        FuelRegistry.INSTANCE.add(ModItems.STAR_FRAGMENT, 4000);
    }
}
