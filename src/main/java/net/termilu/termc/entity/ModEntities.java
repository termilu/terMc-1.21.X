package net.termilu.termc.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.entity.custom.DodoEntity;

public class ModEntities {

    public static final EntityType<DodoEntity> DODO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerMc.MOD_ID, "dodo"),
            EntityType.Builder.create(DodoEntity::new, SpawnGroup.CREATURE).dimensions(1f, 2.5f).build());

    public static void registerModEntities(){
        TerMc.LOGGER.info("Registering Mod Entities for " + TerMc.MOD_ID);
    }
}

