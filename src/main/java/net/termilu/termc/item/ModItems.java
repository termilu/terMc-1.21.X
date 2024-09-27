package net.termilu.termc.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.entity.ModEntities;
import net.termilu.termc.item.custom.*;

import java.util.List;

public class ModItems {
    private static final int swordBaseAttackDamage = 3;
    private static final float swordBaseAttackSpeed = -2.4f;
    private static final int pickaxeBaseAttackDamage = 1;
    private static final float pickaxeBaseAttackSpeed = -2.8f;
    private static final float shovelBaseAttackDamage = 1.5f;
    private static final float shovelBaseAttackSpeed = -3.0f;
    private static final int axeBaseAttackDamage = 6;
    private static final float axeBaseAttackSpeed = -3.2f;
    private static final int hoeBaseAttackDamage = 0;
    private static final float hoeBaseAttackSpeed = -3.0f;


    //Creating new Items
    public static final Item FLUORITE = registerItem("fluorite", new Item(new Item.Settings()));
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", new Item(new Item.Settings()));
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    //Custom items
    public static final Item CHAINSAW = registerItem("chainsaw", new ChainsawItem(new Item.Settings().maxDamage(64)));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(64)));

    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new Item.Settings().maxDamage(200)));
    public static final Item DATA_TABLET = registerItem("data_tablet", new DataTabletItem(new Item.Settings().maxCount(1)));

    public static final Item LAPIS_TORCH = registerItem("lapis_torch",
            new LapisTorchItem(ModBlocks.LAPIS_TORCH, ModBlocks.LAPIS_WALL_TORCH, new Item.Settings(), Direction.DOWN));

    public static final Item EMERALD_TORCH = registerItem("emerald_torch",
            new EmeraldTorchItem(ModBlocks.EMERALD_TORCH, ModBlocks.EMERALD_WALL_TORCH, new Item.Settings(), Direction.DOWN));

    //Spawn eggs
    public static final Item DODO_SPAWN_EGG = registerItem("dodo_spawn_egg",
            //Spawn egg colors
            new SpawnEggItem(ModEntities.DODO, 0x465ae0, 0x545978, new Item.Settings()));

    public static final Item CAPYBARA_SPAWN_EGG = registerItem("capybara_spawn_egg",
            //Spawn egg colors
            new SpawnEggItem(ModEntities.CAPYBARA, 0x94734c, 0x46423e, new Item.Settings()));

    //Food
    public static final Item STRAWBERRY = registerItem("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)){
        //anonymous class to add custom tooltips for items that don't have their own class
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.termc.strawberry.tooltip.1"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item ORANGE = registerItem("orange", new Item(new Item.Settings().food(ModFoodComponents.ORANGE)){
        //anonymous class to add custom tooltips for items that don't have their own class
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.termc.orange.tooltip.1"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item MANGO = registerItem("mango", new Item(new Item.Settings().food(ModFoodComponents.MANGO)){
        //anonymous class to add custom tooltips for items that don't have their own class
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.termc.mango.tooltip.1"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    //Fuel
    public static final Item STAR_FRAGMENT = registerItem("star_fragment", new Item(new Item.Settings()){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.termc.star_fragment.tooltip.1"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    //Sword baseAttackDamage always 3, baseAttackSpeed always -2.4f
    //Tools & Weapons
    public static final Item POISONED_DIAMOND_SWORD = registerItem("poisoned_diamond_sword",
            new ModEffectSwordItem(ToolMaterials.DIAMOND,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND,
                            //Sword baseAttackDamage always 3, baseAttackSpeed always -2.4f
                            swordBaseAttackDamage, swordBaseAttackSpeed)), StatusEffects.POISON) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.termc.poisoned_diamond_sword.tooltip.1"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });

    public static final Item TERM_BOW = registerItem("term_bow",
            new TermBowItem(new Item.Settings().maxDamage(500)));   //maxDamage is durability of the tool

    public static final Item FLUORITE_SWORD = registerItem("fluorite_sword",
            new SwordItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.FLUORITE,
                            //Sword baseAttackDamage always 3, baseAttackSpeed always -2.4f
                            swordBaseAttackDamage, swordBaseAttackSpeed))));

    public static final Item FLUORITE_PICKAXE = registerItem("fluorite_pickaxe",
            new PickaxeItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(
                            //Pickaxe default default 1 baseAttackDamage, -2.8f attackSpeed
                            ModToolMaterials.FLUORITE, pickaxeBaseAttackDamage, pickaxeBaseAttackSpeed))));

    public static final Item FLUORITE_SHOVEL = registerItem("fluorite_shovel",
            new ShovelItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.FLUORITE,
                            shovelBaseAttackDamage, shovelBaseAttackSpeed))));

    public static final Item FLUORITE_AXE = registerItem("fluorite_axe",
            new AxeItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE,
                            axeBaseAttackDamage, axeBaseAttackSpeed))));

    public static final Item FLUORITE_HOE = registerItem("fluorite_hoe",
            new HoeItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.FLUORITE,
                            hoeBaseAttackDamage, hoeBaseAttackSpeed))));

    public static final Item FLUORITE_MULTITOOL = registerItem("fluorite_multitool",
            new MultiToolItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(
                            ModToolMaterials.FLUORITE, 6, -3f))));

    public static final Item FLUORITE_HAMMER = registerItem("fluorite_hammer",
            new HammerItem(ModToolMaterials.FLUORITE,
                    new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(
                            ModToolMaterials.FLUORITE, 5, -2f))));


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
