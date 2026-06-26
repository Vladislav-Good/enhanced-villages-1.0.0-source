package com.enhancedvillages.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MarketStallBlock extends Block {

    public MarketStallBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // Показываем информацию о рынке при взаимодействии
            player.sendMessage(Text.translatable("block.enhancedvillages.market_stall.info"), false);

            // Если у игрока нет товаров в руке - показываем подсказку
            ItemStack handStack = player.getStackInHand(hand);
            if (handStack.isEmpty()) {
                player.sendMessage(Text.translatable("block.enhancedvillages.market_stall.empty"), true);
            } else {
                player.sendMessage(Text.literal("§aТовар: " + handStack.getName().getString()), true);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
