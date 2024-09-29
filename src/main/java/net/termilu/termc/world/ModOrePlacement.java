package net.termilu.termc.world;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

/**
 * Utility class for creating ore placement modifiers.
 */
public class ModOrePlacement {

    /**
     * Creates a list of placement modifiers with the given count and height modifiers.
     *
     * @param countModifier The placement modifier that controls the count of ore veins.
     * @param heightModifier The placement modifier that controls the height range of ore veins.
     * @return A list of placement modifiers.
     */
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    /**
     * Creates a list of placement modifiers with a specified count and height modifier.
     *
     * @param count The number of ore veins to generate.
     * @param heightModifier The placement modifier that controls the height range of ore veins.
     * @return A list of placement modifiers.
     */
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    /**
     * Creates a list of placement modifiers with a specified rarity and height modifier.
     *
     * @param chance The chance of generating an ore vein.
     * @param heightModifier The placement modifier that controls the height range of ore veins.
     * @return A list of placement modifiers.
     */
    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

}