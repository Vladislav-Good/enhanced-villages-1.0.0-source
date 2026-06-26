package com.enhancedvillages.entity;

import com.enhancedvillages.EnhancedVillages;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    /**
     * Сущность торгового каравана — движущийся NPC на лошади с сундуком
     */
    public static final EntityType<CaravanEntity> CARAVAN = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(EnhancedVillages.MOD_ID, "caravan"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CaravanEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 1.8f))
                    .build()
    );

    /**
     * Стражник деревни — защищает жителей, атакует зомби/скелетов/разбойников
     */
    public static final EntityType<VillageGuardEntity> VILLAGE_GUARD = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(EnhancedVillages.MOD_ID, "village_guard"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, VillageGuardEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                    .build()
    );

    /**
     * Житель с заданием — специальный NPC с уникальным диалогом
     */
    public static final EntityType<QuestVillagerEntity> QUEST_VILLAGER = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(EnhancedVillages.MOD_ID, "quest_villager"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, QuestVillagerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                    .build()
    );

    public static void register() {
        EnhancedVillages.LOGGER.info("Registered Enhanced Villages entities");
        // Типы регистрируются автоматически выше через Registry.register
    }
}
