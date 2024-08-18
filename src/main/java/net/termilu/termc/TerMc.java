package net.termilu.termc;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.entity.ModEntities;
import net.termilu.termc.entity.custom.CapybaraEntity;
import net.termilu.termc.entity.custom.DodoEntity;
import net.termilu.termc.item.ModItemGroups;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.sound.ModSounds;
import net.termilu.termc.util.Fueltems;
import net.termilu.termc.util.HammerUsageEvent;
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

		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.DODO, DodoEntity.createDodoAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CAPYBARA, CapybaraEntity.createCapybaraAttributes());

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
	}
}