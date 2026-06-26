package com.enhancedvillages.trade;

import com.enhancedvillages.EnhancedVillages;
import com.enhancedvillages.item.ModItems;
import com.enhancedvillages.profession.ModProfessions;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModTradeOffers {

    public static void register() {

        // ======= АЛХИМИК =======
        TradeOfferHelper.registerVillagerOffers(ModProfessions.ALCHEMIST, 1, factories -> {
            // Уровень 1: Базовые зелья
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.GLASS_BOTTLE, 5),
                    new ItemStack(Items.POTION, 1),  // Зелье лечения
                    12, 1, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.NETHER_WART, 1),
                    16, 1, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.ALCHEMIST, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.SPLASH_POTION, 1),  // Плещущееся зелье
                    10, 5, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.BLAZE_POWDER, 3),
                    8, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.ALCHEMIST, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.LINGERING_POTION, 1),  // Стационарное зелье
                    6, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.ALCHEMIST, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.DRAGON_BREATH, 1),
                    4, 20, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.ALCHEMIST, 5, factories -> {
            // Продаёт монеты деревни
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ModItems.VILLAGE_COIN, 5),
                    4, 30, 0.05f
            ));
        });

        // ======= ТОРГОВЕЦ =======
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MERCHANT, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.TROPICAL_FISH, 3),
                    12, 1, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.CACTUS, 4),
                    12, 1, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MERCHANT, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.BAMBOO, 8),
                    10, 5, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.COCOA_BEANS, 5),
                    10, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MERCHANT, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.TOTEM_OF_UNDYING, 1),  // Редкий товар!
                    2, 10, 0.05f
            ));
            // Принимает монеты деревни
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ModItems.VILLAGE_COIN, 3),
                    new ItemStack(Items.DIAMOND, 1),
                    6, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MERCHANT, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 25),
                    new ItemStack(Items.ELYTRA, 1),  // Суперредкий товар
                    1, 20, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MERCHANT, 5, factories -> {
            // Торговый контракт
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ModItems.VILLAGE_COIN, 10),
                    new ItemStack(ModItems.TRADE_CONTRACT, 1),
                    3, 30, 0.05f
            ));
        });

        // ======= МАСТЕР-КУЗНЕЦ =======
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MASTER_BLACKSMITH, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.IRON_INGOT, 8),
                    new ItemStack(Items.EMERALD, 1),
                    16, 1, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.IRON_SWORD, 1),
                    12, 1, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MASTER_BLACKSMITH, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.IRON_CHESTPLATE, 1),
                    10, 5, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.GOLD_INGOT, 5),
                    new ItemStack(Items.EMERALD, 1),
                    12, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MASTER_BLACKSMITH, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.DIAMOND_SWORD, 1),
                    6, 10, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.DIAMOND, 3),
                    new ItemStack(Items.EMERALD, 1),
                    8, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MASTER_BLACKSMITH, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.DIAMOND_CHESTPLATE, 1),
                    4, 20, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.MASTER_BLACKSMITH, 5, factories -> {
            // Продаёт зачарованное алмазное снаряжение
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 30),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    2, 30, 0.05f
            ));
        });

        // ======= ПРОВОДНИК КАРАВАНА =======
        TradeOfferHelper.registerVillagerOffers(ModProfessions.CARAVAN_GUIDE, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.MAP, 1),
                    12, 1, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(ModItems.CARAVAN_MAP, 1),
                    8, 1, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.CARAVAN_GUIDE, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.TREASURE_MAP, 1),
                    6, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.CARAVAN_GUIDE, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.SADDLE, 1),
                    8, 10, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.HORSE_ARMOR_GOLD, 1),
                    6, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.CARAVAN_GUIDE, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.HORSE_ARMOR_DIAMOND, 1),
                    4, 20, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.CARAVAN_GUIDE, 5, factories -> {
            // Редкие ресурсы с других земель
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.SPONGE, 2),
                    3, 30, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.PRISMARINE_CRYSTALS, 8),
                    4, 30, 0.05f
            ));
        });

        // ======= СМОТРИТЕЛЬ ЗАДАНИЙ =======
        TradeOfferHelper.registerVillagerOffers(ModProfessions.QUEST_MASTER, 1, factories -> {
            // Продаёт свитки заданий
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.QUEST_SCROLL, 1),
                    12, 1, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.QUEST_MASTER, 2, factories -> {
            // Принимает ресурсы за монеты
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.WHEAT, 20),
                    new ItemStack(ModItems.VILLAGE_COIN, 2),
                    10, 5, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.COAL, 16),
                    new ItemStack(ModItems.VILLAGE_COIN, 1),
                    12, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.QUEST_MASTER, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 20),
                    new ItemStack(ModItems.VILLAGE_COIN, 3),
                    10, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.QUEST_MASTER, 5, factories -> {
            // Лучшая награда — медальон стражника
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ModItems.VILLAGE_COIN, 20),
                    new ItemStack(ModItems.GUARD_MEDALLION, 1),
                    1, 30, 0.05f
            ));
        });

        // ======= ТАВЕРЩИК =======
        TradeOfferHelper.registerVillagerOffers(ModProfessions.INNKEEPER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.BREAD, 6),
                    16, 1, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.COOKED_CHICKEN, 4),
                    16, 1, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.INNKEEPER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.COOKED_BEEF, 4),
                    12, 5, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.GOLDEN_CARROT, 4),
                    10, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.INNKEEPER, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.GOLDEN_APPLE, 1),
                    6, 10, 0.05f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.CAKE, 1),
                    8, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModProfessions.INNKEEPER, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ModItems.VILLAGE_COIN, 5),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    2, 30, 0.05f
            ));
        });

        EnhancedVillages.LOGGER.info("Registered Enhanced Villages trade offers");
    }
}
