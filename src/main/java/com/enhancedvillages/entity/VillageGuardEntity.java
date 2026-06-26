package com.enhancedvillages.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

// Используем PathAwareEntity как базу для стражника
import net.minecraft.entity.mob.PathAwareEntity;

public class VillageGuardEntity extends PathAwareEntity {

    private static final String[] GUARD_DIALOGUES = {
        "§c[Стражник] §fСтой! Назови себя, путник.",
        "§c[Стражник] §fДеревня под защитой! Проходи с миром.",
        "§c[Стражник] §fЗамечено движение монстров ночью. Будь осторожен!",
        "§c[Стражник] §fДобро пожаловать в нашу деревню!",
        "§c[Стражник] §fЯ охраняю деревню с рассвета до заката.",
    };

    public VillageGuardEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createGuardAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_ARMOR, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void initGoals() {
        // Приоритеты поведения стражника
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));

        // Цели атаки — враги деревни
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, ZombieEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, SkeletonEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PillagerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, RavagerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, VexEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    @Override
    protected void initEquipment(net.minecraft.util.math.random.Random random,
                                  net.minecraft.world.LocalDifficulty difficulty) {
        // Стражник экипирован железной бронёй и мечом
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
        this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
        this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            // Случайный диалог стражника
            String dialogue = GUARD_DIALOGUES[(int)(Math.random() * GUARD_DIALOGUES.length)];
            player.sendMessage(Text.literal(dialogue), false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false; // Стражники не исчезают
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false; // Не горит на солнце
    }
}
