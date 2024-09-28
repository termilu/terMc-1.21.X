package net.termilu.termc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.components.ModDataComponentTypes;
import net.termilu.termc.entity.ModEntities;
import net.termilu.termc.entity.custom.CapybaraEntity;
import net.termilu.termc.entity.custom.DodoEntity;
import net.termilu.termc.event.AttackEntityHandler;
import net.termilu.termc.event.UseBlockCallbackHandler;
import net.termilu.termc.event.UseItemCallbackHandler;
import net.termilu.termc.item.ModItemGroups;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.sound.ModSounds;
import net.termilu.termc.util.Fueltems;
import net.termilu.termc.util.HammerUsageEvent;
import net.termilu.termc.world.gen.ModWorldGeneration;
import net.termilu.termc.worldgen.ModEntitySpawns;
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
		ModEntitySpawns.addSpawns();
		FabricDefaultAttributeRegistry.register(ModEntities.DODO, DodoEntity.createDodoAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CAPYBARA, CapybaraEntity.createCapybaraAttributes());

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		ModDataComponentTypes.registerDataComponentTypes();

		AttackEntityCallback.EVENT.register(new AttackEntityHandler());
		UseItemCallback.EVENT.register(new UseItemCallbackHandler());
		UseBlockCallback.EVENT.register(new UseBlockCallbackHandler());

		ModWorldGeneration.generateModWorldGeneration();

		registerStrippables();
		registerFlammables();
	}

	private static void registerStrippables() {
		StrippableBlockRegistry.register(ModBlocks.BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.BLACKWOOD_WOOD, ModBlocks.STRIPPED_BLACKWOOD_WOOD);
	}

	private static void registerFlammables() {
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACKWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACKWOOD_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_LEAVES, 30, 60);
	}
}