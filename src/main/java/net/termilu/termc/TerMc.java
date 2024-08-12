package net.termilu.termc;

import net.fabricmc.api.ModInitializer;

import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.item.ModItemGroups;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.sound.ModSounds;
import net.termilu.termc.util.Fueltems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerMc implements ModInitializer {

	public static final String MOD_ID = "termc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		Fueltems.registerFuelItems();
		ModSounds.registerSounds();
	}
}