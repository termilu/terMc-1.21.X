package net.termilu.termc.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.math.Direction;

public class EmeraldTorchItem extends VerticallyAttachableBlockItem {
    /**
     * @param standingBlock
     * @param wallBlock
     * @param settings
     * @param verticalAttachmentDirection the direction of the item's vertical attachment, {@link Direction#UP} for hanging blocks
     *                                    and {@link Direction#DOWN} for standing blocks
     */
    public EmeraldTorchItem(Block standingBlock, Block wallBlock, Settings settings, Direction verticalAttachmentDirection) {
        super(standingBlock, wallBlock, settings, verticalAttachmentDirection);
    }
}
