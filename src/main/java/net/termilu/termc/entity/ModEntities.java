package net.termilu.termc.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.entity.custom.CapybaraEntity;
import net.termilu.termc.entity.custom.DodoEntity;

public class ModEntities {

    public static final EntityType<DodoEntity> DODO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerMc.MOD_ID, "dodo"),
            //Hitbox
            EntityType.Builder.create(DodoEntity::new, SpawnGroup.CREATURE).dimensions(1f, 2.5f).build());

    public static final EntityType<CapybaraEntity> CAPYBARA = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerMc.MOD_ID, "capybara"),
            //Hitbox
            EntityType.Builder.create(CapybaraEntity::new, SpawnGroup.CREATURE).dimensions(1f, 0.8f).build());

    public static void registerModEntities(){
        TerMc.LOGGER.info("Registering Mod Entities for " + TerMc.MOD_ID);
    }
}

