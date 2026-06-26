package com.enhancedvillages.profession;

import com.enhancedvillages.EnhancedVillages;
import com.enhancedvillages.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

public class ModProfessions {

    /**
     * АЛХИМИК — работает у Алхимического котла.
     * Торгует зельями, ингредиентами и зачарованными предметами.
     */
    public static final VillagerProfession ALCHEMIST = VillagerProfessionBuilder.create()
            .id(new Identifier(EnhancedVillages.MOD_ID, "alchemist"))
            .workstation(ModBlocks.ALCHEMIST_CAULDRON)
            .workSound(SoundEvents.BLOCK_BREWING_STAND_BREW)
            .build();

    /**
     * ТОРГОВЕЦ (Merchant) — работает у Торгового прилавка.
     * Продаёт редкие и экзотические товары с других земель.
     */
    public static final VillagerProfession MERCHANT = VillagerProfessionBuilder.create()
            .id(new Identifier(EnhancedVillages.MOD_ID, "merchant"))
            .workstation(ModBlocks.MARKET_STALL)
            .workSound(SoundEvents.ENTITY_VILLAGER_TRADE)
            .build();

    /**
     * КУЗНЕЦ-МАСТЕР (Master Blacksmith) — работает у кузнецкого наковальни.
     * Создаёт улучшенные инструменты и броню с особыми чарами.
     */
    public static final VillagerProfession MASTER_BLACKSMITH = VillagerProfessionBuilder.create()
            .id(new Identifier(EnhancedVillages.MOD_ID, "master_blacksmith"))
            .workstation(ModBlocks.BLACKSMITH_ANVIL)
            .workSound(SoundEvents.BLOCK_ANVIL_USE)
            .build();

    /**
     * ПРОВОДНИК КАРАВАНА (Caravan Guide) — работает у Знака каравана.
     * Организует торговые маршруты и продаёт карты сокровищ.
     */
    public static final VillagerProfession CARAVAN_GUIDE = VillagerProfessionBuilder.create()
            .id(new Identifier(EnhancedVillages.MOD_ID, "caravan_guide"))
            .workstation(ModBlocks.CARAVAN_FLAG)
            .workSound(SoundEvents.ENTITY_HORSE_SADDLE)
            .build();

    /**
     * СМОТРИТЕЛЬ ЗАДАНИЙ (Quest Master) — работает у Доски заданий.
     * Выдаёт задания и принимает отчёты об их выполнении.
     */
    public static final VillagerProfession QUEST_MASTER = VillagerProfessionBuilder.create()
            .id(new Identifier(EnhancedVillages.MOD_ID, "quest_master"))
            .workstation(ModBlocks.QUEST_BOARD)
            .workSound(SoundEvents.ITEM_BOOK_PAGE_TURN)
            .build();

    /**
     * ТАВЕРЩИК (Innkeeper) — работает у Торгового сундука.
     * Предлагает ночлег (эффекты насыщения) и еду.
     */
    public static final VillagerProfession INNKEEPER = VillagerProfessionBuilder.create()
            .id(new Identifier(EnhancedVillages.MOD_ID, "innkeeper"))
            .workstation(ModBlocks.MERCHANT_CHEST)
            .workSound(SoundEvents.BLOCK_CHEST_OPEN)
            .build();

    public static void register() {
        Registry.register(Registries.VILLAGER_PROFESSION,
                new Identifier(EnhancedVillages.MOD_ID, "alchemist"), ALCHEMIST);
        Registry.register(Registries.VILLAGER_PROFESSION,
                new Identifier(EnhancedVillages.MOD_ID, "merchant"), MERCHANT);
        Registry.register(Registries.VILLAGER_PROFESSION,
                new Identifier(EnhancedVillages.MOD_ID, "master_blacksmith"), MASTER_BLACKSMITH);
        Registry.register(Registries.VILLAGER_PROFESSION,
                new Identifier(EnhancedVillages.MOD_ID, "caravan_guide"), CARAVAN_GUIDE);
        Registry.register(Registries.VILLAGER_PROFESSION,
                new Identifier(EnhancedVillages.MOD_ID, "quest_master"), QUEST_MASTER);
        Registry.register(Registries.VILLAGER_PROFESSION,
                new Identifier(EnhancedVillages.MOD_ID, "innkeeper"), INNKEEPER);

        EnhancedVillages.LOGGER.info("Registered Enhanced Villages professions");
    }
}
