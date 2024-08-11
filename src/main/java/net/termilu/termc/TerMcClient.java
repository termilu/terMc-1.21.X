package net.termilu.termc;

import net.fabricmc.api.ClientModInitializer;
import net.termilu.termc.item.ModItems;

public class TerMcClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModItems.registerModItems();
    }
}
