package net.termilu.termc.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
public class ModItems {
    //Creating new Items
    public static final Item FLUORITE = registerItem("fluorite", new Item(new Item.Settings()));
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", new Item(new Item.Settings()));

    //Registering item with unique identifier
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TerMc.MOD_ID, name), item);
    }

    //Add Items to "Ingredients" tab in creative menu
    private static void customIngredients(FabricItemGroupEntries entries){
        entries.add(FLUORITE);
        entries.add(RAW_FLUORITE);
    }

    public static void registerModItems(){
        TerMc.LOGGER.info("Registering Mod Items for: " + TerMc.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
