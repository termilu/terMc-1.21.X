package net.termilu.termc.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.termilu.termc.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

/**
 * Custom armor item class that applies status effects when a full set of specific armor is worn.
 */
public class ModArmorItem extends ArmorItem {
    /**
     * Map associating custom armor materials with their respective status effects.
     */
    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    // Can only apply Status effects for non-vanilla armor materials
                    .put(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL,
                            List.of(new StatusEffectInstance(StatusEffects.HASTE,
                                            400, 0, false, false),
                                    new StatusEffectInstance(StatusEffects.SPEED,
                                            400, 0, false, false))).build();
                    // Add more armor materials and status effects here...

    /**
     * Constructor for ModArmorItem.
     *
     * @param material The armor material.
     * @param type The type of armor (helmet, chestplate, leggings, boots).
     * @param settings The item settings.
     */
    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    /**
     * Called each tick while the item is in a player's inventory.
     *
     * @param stack The item stack.
     * @param world The world.
     * @param entity The entity holding the item.
     * @param slot The inventory slot.
     * @param selected Whether the item is selected.
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    /**
     * Evaluates and applies the appropriate status effects if the player is wearing a full set of custom armor.
     *
     * @param player The player entity.
     */
    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    /**
     * Adds the status effects to the player if they are not already present.
     *
     * @param player The player entity.
     * @param mapArmorMaterial The armor material.
     * @param mapStatusEffect The list of status effects.
     */
    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    /**
     * Checks if the player is wearing a full set of armor.
     *
     * @param player The player entity.
     * @return True if the player is wearing a full set of armor, false otherwise.
     */
    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    /**
     * Checks if the player is wearing the correct type of armor material.
     *
     * @param material The armor material.
     * @param player The player entity.
     * @return True if the player is wearing the correct type of armor material, false otherwise.
     */
    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        // Check that the player is wearing actual armor, not something else (Pumpkin, Elytra, etc.).
        // If left unchecked the game will crash when trying to cast the item to ArmorItem.
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //if Tooltip is showing and shift is held down then display custom tooltip
        if(!Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.termc.general.tooltip.shift"));
        }else{
            tooltip.add(Text.translatable("tooltip.termc.fluorite_armor_item.tooltip.1"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}