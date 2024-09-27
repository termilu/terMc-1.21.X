package net.termilu.termc.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.termilu.termc.TerMc;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * This class defines custom armor materials for the mod.
 */
public class ModArmorMaterials {

    /**
     * The custom armor material for Fluorite armor.
     * This material has specific properties such as durability, protection amounts, and repair ingredient.
     */
    public static final RegistryEntry<ArmorMaterial> FLUORITE_ARMOR_MATERIAL = registerArmorMaterial("fluorite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3); // Protection for helmet
                        map.put(ArmorItem.Type.CHESTPLATE, 7); // Protection for chestplate
                        map.put(ArmorItem.Type.LEGGINGS, 6); // Protection for leggings
                        map.put(ArmorItem.Type.BOOTS, 3); // Protection for boots

                        map.put(ArmorItem.Type.BODY, 4); // Protection for animal armor
                    }),
                    20, // Enchantability
                    SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, // Equip sound
                    () -> Ingredient.ofItems(ModItems.FLUORITE), // Repair ingredient
                    List.of(new ArmorMaterial.Layer(Identifier.of(TerMc.MOD_ID, "fluorite"))), // Texture layers from /assets/termc/textures/models/armor
                    1, // Toughness
                    0 // Knockback resistance
            ));

    // Add more custom armor materials here...

    /**
     * Registers a custom armor material.
     *
     * @param name The name of the armor material.
     * @param material A supplier that provides the armor material.
     * @return A registry entry for the armor material.
     */
    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(TerMc.MOD_ID, name), material.get());
    }
}