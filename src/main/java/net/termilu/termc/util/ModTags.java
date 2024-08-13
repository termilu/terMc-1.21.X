package net.termilu.termc.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;

public class ModTags {
    public static class Blocks{
        //Custom Block Tags

        public static final TagKey<Block> NEEDS_FLUORITE_TOOL = createTag("needs_fluorite_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLUORITE_TOOL = createTag("incorrect_for_fluorite_tool");

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TerMc.MOD_ID, name));

        }
    }

    public static class Items{
        //Custom Item Tags
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TerMc.MOD_ID, name));

        }
    }
}
