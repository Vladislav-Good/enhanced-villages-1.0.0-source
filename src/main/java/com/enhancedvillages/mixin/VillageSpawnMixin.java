package com.enhancedvillages.mixin;

import com.enhancedvillages.world.ModStructures;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin для добавления стражников и квестовых жителей при спавне жителя деревни.
 * С некоторой вероятностью заменяет обычного жителя на стражника или квестового жителя.
 */
@Mixin(VillagerEntity.class)
public class VillageSpawnMixin {

    /**
     * При инициализации нового жителя — иногда спавним стражников рядом.
     */
    @Inject(method = "onSpawnedTogether", at = @At("TAIL"))
    private void onVillagerSpawn(CallbackInfo ci) {
        VillagerEntity self = (VillagerEntity)(Object)this;

        if (self.getWorld() instanceof ServerWorld serverWorld) {
            // С вероятностью 15% спавним квестового жителя рядом с новым жителем
            if (serverWorld.getRandom().nextFloat() < 0.15f) {
                BlockPos pos = self.getBlockPos().add(
                        serverWorld.getRandom().nextInt(5) - 2,
                        0,
                        serverWorld.getRandom().nextInt(5) - 2
                );
                ModStructures.spawnQuestVillager(serverWorld, pos);
            }

            // С вероятностью 5% — спавним стражников и карван (при большом поселении)
            if (serverWorld.getRandom().nextFloat() < 0.05f) {
                ModStructures.spawnVillageGuards(serverWorld, self.getBlockPos(), serverWorld.getRandom());
            }
        }
    }
}
