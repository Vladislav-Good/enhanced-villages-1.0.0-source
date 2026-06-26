package com.enhancedvillages.world;

import com.enhancedvillages.EnhancedVillages;
import com.enhancedvillages.entity.ModEntities;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.VillagePlace;
import net.minecraft.world.Heightmap;

import java.util.List;

/**
 * Отвечает за генерацию новых структур в деревнях:
 * - Рынок (Market)
 * - Таверна / Постоялый двор (Inn)
 * - Остановка каравана (Caravan Stop)
 * - Сторожевые посты (Guard Posts)
 *
 * Структуры задаются через JSON-файлы в /data/enhancedvillages/structures/
 * и регистрируются через Village Structure Pool (Jigsaw).
 */
public class ModStructures {

    public static void register() {
        // Спавн каравана и стражников в деревнях при загрузке мира
        ServerWorldEvents.LOAD.register((server, world) -> {
            // Каждые 6000 тиков (5 минут) — спавн каравана
            world.getServer().execute(() -> {
                EnhancedVillages.LOGGER.info("Enhanced Villages: World loaded, village spawns scheduled");
            });
        });

        EnhancedVillages.LOGGER.info("Registered Enhanced Villages structures");
    }

    /**
     * Попытка заспавнить стражника рядом с деревней.
     * Вызывается при генерации деревни через Mixin.
     */
    public static void spawnVillageGuards(ServerWorld world, BlockPos villageCenter, Random random) {
        int guardsToSpawn = 2 + random.nextInt(3); // 2-4 стражника

        for (int i = 0; i < guardsToSpawn; i++) {
            int offsetX = (random.nextInt(20) - 10);
            int offsetZ = (random.nextInt(20) - 10);
            BlockPos spawnPos = world.getTopPosition(
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    villageCenter.add(offsetX, 0, offsetZ)
            );

            var guard = ModEntities.VILLAGE_GUARD.create(world);
            if (guard != null) {
                guard.refreshPositionAndAngles(
                        spawnPos.getX() + 0.5,
                        spawnPos.getY(),
                        spawnPos.getZ() + 0.5,
                        random.nextFloat() * 360f, 0f
                );
                world.spawnEntityAndPassengers(guard);
            }
        }
    }

    /**
     * Спавн торгового каравана в деревне раз в игровой день.
     */
    public static void spawnCaravan(ServerWorld world, BlockPos villageCenter) {
        BlockPos spawnPos = world.getTopPosition(
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                villageCenter.add(5, 0, 0)
        );

        var caravan = ModEntities.CARAVAN.create(world);
        if (caravan != null) {
            caravan.refreshPositionAndAngles(
                    spawnPos.getX() + 0.5,
                    spawnPos.getY(),
                    spawnPos.getZ() + 0.5,
                    0f, 0f
            );
            world.spawnEntityAndPassengers(caravan);
            EnhancedVillages.LOGGER.info("Spawned caravan at " + spawnPos);
        }
    }

    /**
     * Спавн жителя с заданием в деревне.
     */
    public static void spawnQuestVillager(ServerWorld world, BlockPos pos) {
        var questVillager = ModEntities.QUEST_VILLAGER.create(world);
        if (questVillager != null) {
            questVillager.refreshPositionAndAngles(
                    pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0f, 0f
            );
            world.spawnEntityAndPassengers(questVillager);
        }
    }
}
