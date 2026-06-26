package com.enhancedvillages.entity;

import com.enhancedvillages.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;

import java.util.Random;

public class CaravanEntity extends MerchantEntity {

    // Диалоги каравана при взаимодействии
    private static final String[] CARAVAN_GREETINGS = {
        "§6[Торговец Каравана] §eПриветствую, путник! Взгляните на мои редкие товары!",
        "§6[Торговец Каравана] §eМы прибыли издалека! У нас есть то, чего нет в деревне!",
        "§6[Торговец Каравана] §eТорговый путь долог, но товары того стоят!",
        "§6[Торговец Каравана] §eСвежие товары из далёких земель, по лучшим ценам!",
    };

    public CaravanEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createCaravanAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PlayerEntity.class, 6.0f, 1.0, 1.2));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 0.35));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient && !this.isTrading() && !this.isSleeping()) {
            // Показываем случайное приветствие
            String greeting = CARAVAN_GREETINGS[new Random().nextInt(CARAVAN_GREETINGS.length)];
            player.sendMessage(Text.literal(greeting), false);

            // Открываем торговый интерфейс
            this.beginTradeWith(player);
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    @Override
    protected void fillRecipes() {
        TradeOfferList offers = this.getOffers();

        // Уникальные товары каравана (не доступны в обычной деревне)
        offers.add(new TradeOffer(
                new ItemStack(Items.EMERALD, 8),
                new ItemStack(Items.HEART_OF_THE_SEA, 1),
                2, 10, 0.1f
        ));
        offers.add(new TradeOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(Items.MUSIC_DISC_13, 1),
                1, 10, 0.1f
        ));
        offers.add(new TradeOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(Items.SHULKER_SHELL, 3),
                4, 10, 0.1f
        ));
        offers.add(new TradeOffer(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(ModItems.CARAVAN_MAP, 1),
                8, 5, 0.05f
        ));
        offers.add(new TradeOffer(
                new ItemStack(Items.EMERALD, 6),
                new ItemStack(Items.NAUTILUS_SHELL, 2),
                6, 5, 0.05f
        ));
        offers.add(new TradeOffer(
                new ItemStack(ModItems.VILLAGE_COIN, 5),
                new ItemStack(Items.NETHERITE_SCRAP, 1),
                3, 15, 0.1f
        ));
    }

    @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_VILLAGER_AMBIENT; }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.ENTITY_VILLAGER_HURT; }

    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_VILLAGER_DEATH; }

    @Override
    public SoundEvent getYesSound() { return SoundEvents.ENTITY_VILLAGER_YES; }

    @Override
    public SoundEvent getNoSound() { return SoundEvents.ENTITY_VILLAGER_NO; }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) { return null; }

    @Override
    public boolean isLeveledMerchant() { return false; }
}
