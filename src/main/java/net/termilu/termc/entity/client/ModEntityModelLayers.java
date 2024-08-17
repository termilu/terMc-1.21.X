package net.termilu.termc.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;

public class ModEntityModelLayers {
    public static final EntityModelLayer DODO =
            new EntityModelLayer(Identifier.of(TerMc.MOD_ID, "dodo"), "main");

    public static final EntityModelLayer CAPYBARA =
            new EntityModelLayer(Identifier.of(TerMc.MOD_ID, "capybara"), "main");
}
