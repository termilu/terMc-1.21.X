package net.termilu.termc.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.item.custom.ChainsawItem;

public class ModItems {
    //Creating new Items
    public static final Item FLUORITE = registerItem("fluorite", new Item(new Item.Settings()));
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", new Item(new Item.Settings()));
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    //Custom items
    public static final Item CHAINSAW = registerItem("chainsaw", new ChainsawItem(new Item.Settings().maxDamage(64)));

    //Food
    public static final Item STRAWBERRY = registerItem("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)));

    //Fuel
    public static final Item STAR_FRAGMENT = registerItem("star_fragment", new Item(new Item.Settings()));

    //Registering item with unique identifier
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TerMc.MOD_ID, name), item);
    }

    //Add Items to "Ingredients" tab in creative menu
    private static void customIngredients(FabricItemGroupEntries entries){
        entries.add(FLUORITE);
        entries.add(RAW_FLUORITE);
        entries.add(PINK_GARNET);
        entries.add(RAW_PINK_GARNET);
    }

    public static void registerModItems(){
        TerMc.LOGGER.info("Registering Mod Items for: " + TerMc.MOD_ID);

        //Add customIngredients into creative "Ingredients" Item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
