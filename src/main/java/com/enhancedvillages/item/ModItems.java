package com.enhancedvillages.item;

import com.enhancedvillages.EnhancedVillages;
import com.enhancedvillages.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Предметы блоков
    public static final Item MARKET_STALL_ITEM = new BlockItem(ModBlocks.MARKET_STALL, new FabricItemSettings());
    public static final Item QUEST_BOARD_ITEM = new BlockItem(ModBlocks.QUEST_BOARD, new FabricItemSettings());
    public static final Item CARAVAN_FLAG_ITEM = new BlockItem(ModBlocks.CARAVAN_FLAG, new FabricItemSettings());
    public static final Item BLACKSMITH_ANVIL_ITEM = new BlockItem(ModBlocks.BLACKSMITH_ANVIL, new FabricItemSettings());
    public static final Item ALCHEMIST_CAULDRON_ITEM = new BlockItem(ModBlocks.ALCHEMIST_CAULDRON, new FabricItemSettings());
    public static final Item MERCHANT_CHEST_ITEM = new BlockItem(ModBlocks.MERCHANT_CHEST, new FabricItemSettings());

    // Уникальные предметы мода
    // Торговый контракт - выдаётся после выполнения задания
    public static final Item TRADE_CONTRACT = new TradeContractItem(new FabricItemSettings().maxCount(16));

    // Монета деревни - валюта для торговли на рынке
    public static final Item VILLAGE_COIN = new VillageCoinItem(new FabricItemSettings().maxCount(64));

    // Карта каравана - показывает маршрут каравана
    public static final Item CARAVAN_MAP = new CaravanMapItem(new FabricItemSettings().maxCount(1));

    // Медальон стражника - даёт защиту в деревне
    public static final Item GUARD_MEDALLION = new GuardMedallionItem(new FabricItemSettings().maxCount(1));

    // Свиток задания - выдаётся с доски заданий
    public static final Item QUEST_SCROLL = new QuestScrollItem(new FabricItemSettings().maxCount(8));

    public static void register() {
        // Блоковые предметы
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "market_stall"), MARKET_STALL_ITEM);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "quest_board"), QUEST_BOARD_ITEM);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "caravan_flag"), CARAVAN_FLAG_ITEM);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "blacksmith_anvil"), BLACKSMITH_ANVIL_ITEM);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "alchemist_cauldron"), ALCHEMIST_CAULDRON_ITEM);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "merchant_chest"), MERCHANT_CHEST_ITEM);

        // Уникальные предметы
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "trade_contract"), TRADE_CONTRACT);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "village_coin"), VILLAGE_COIN);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "caravan_map"), CARAVAN_MAP);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "guard_medallion"), GUARD_MEDALLION);
        Registry.register(Registries.ITEM, new Identifier(EnhancedVillages.MOD_ID, "quest_scroll"), QUEST_SCROLL);

        EnhancedVillages.LOGGER.info("Registered Enhanced Villages items");
    }
}
