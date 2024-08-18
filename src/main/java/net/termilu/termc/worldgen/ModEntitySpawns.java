package net.termilu.termc.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.termilu.termc.entity.ModEntities;

public class ModEntitySpawns {
    public static void addSpawns(){
        //Capybara

        //Define biomes to spawn in
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.BEACH, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.RIVER,
                        BiomeKeys.GROVE, BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA),
                SpawnGroup.CREATURE, ModEntities.CAPYBARA, 50, 4, 15);

        SpawnRestriction.register(ModEntities.CAPYBARA, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);


        //Dodo
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.BEACH, BiomeKeys.SAVANNA, BiomeKeys.FOREST,
                        BiomeKeys.JUNGLE, BiomeKeys.STONY_PEAKS),
                SpawnGroup.CREATURE, ModEntities.DODO, 50, 1, 5);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DESERT, BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.WOODED_BADLANDS),
                SpawnGroup.CREATURE, ModEntities.DODO, 200, 2, 5);

        SpawnRestriction.register(ModEntities.DODO, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

        //Hostile entity example
        //SpawnRestriction.register(ModEntities.HOSTILE_MOB, SpawnLocationTypes.ON_GROUND,
        //        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
    }
}
