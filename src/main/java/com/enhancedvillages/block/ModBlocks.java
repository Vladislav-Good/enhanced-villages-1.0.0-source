package com.enhancedvillages.block;

import com.enhancedvillages.EnhancedVillages;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Рыночный прилавок
    public static final Block MARKET_STALL = new MarketStallBlock(
            FabricBlockSettings.of(Material.WOOD)
                    .strength(2.5f)
                    .sounds(BlockSoundGroup.WOOD)
    );

    // Доска заданий
    public static final Block QUEST_BOARD = new QuestBoardBlock(
            FabricBlockSettings.of(Material.WOOD)
                    .strength(2.0f)
                    .sounds(BlockSoundGroup.WOOD)
    );

    // Знак каравана
    public static final Block CARAVAN_FLAG = new CaravanFlagBlock(
            FabricBlockSettings.of(Material.WOOD)
                    .strength(1.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .nonOpaque()
    );

    // Кузница (расширенная)
    public static final Block BLACKSMITH_ANVIL = new BlacksmithAnvilBlock(
            FabricBlockSettings.of(Material.METAL)
                    .strength(5.0f, 1200.0f)
                    .sounds(BlockSoundGroup.METAL)
    );

    // Алхимический котёл
    public static final Block ALCHEMIST_CAULDRON = new AlchemistCauldronBlock(
            FabricBlockSettings.of(Material.METAL)
                    .strength(3.0f)
                    .sounds(BlockSoundGroup.METAL)
    );

    // Торговый сундук (сундук с товарами)
    public static final Block MERCHANT_CHEST = new MerchantChestBlock(
            FabricBlockSettings.of(Material.WOOD)
                    .strength(2.5f)
                    .sounds(BlockSoundGroup.WOOD)
    );

    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier(EnhancedVillages.MOD_ID, "market_stall"), MARKET_STALL);
        Registry.register(Registries.BLOCK, new Identifier(EnhancedVillages.MOD_ID, "quest_board"), QUEST_BOARD);
        Registry.register(Registries.BLOCK, new Identifier(EnhancedVillages.MOD_ID, "caravan_flag"), CARAVAN_FLAG);
        Registry.register(Registries.BLOCK, new Identifier(EnhancedVillages.MOD_ID, "blacksmith_anvil"), BLACKSMITH_ANVIL);
        Registry.register(Registries.BLOCK, new Identifier(EnhancedVillages.MOD_ID, "alchemist_cauldron"), ALCHEMIST_CAULDRON);
        Registry.register(Registries.BLOCK, new Identifier(EnhancedVillages.MOD_ID, "merchant_chest"), MERCHANT_CHEST);

        EnhancedVillages.LOGGER.info("Registered Enhanced Villages blocks");
    }
}
