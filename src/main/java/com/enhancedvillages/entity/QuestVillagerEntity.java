package com.enhancedvillages.entity;

import com.enhancedvillages.item.ModItems;
import com.enhancedvillages.quest.QuestManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class QuestVillagerEntity extends PathAwareEntity {

    // Уникальный тип задания этого жителя
    private String assignedQuestType = "GATHER";
    private boolean questGiven = false;

    // Диалоги NPC при разных состояниях
    private static final String[] QUEST_GREETINGS = {
        "§a[Житель] §fПривет, приключенец! Не могли бы вы мне помочь?",
        "§a[Житель] §fО, искатель приключений! У меня есть для вас задание.",
        "§a[Житель] §fНаконец-то кто-то пришёл! Мне нужна помощь!",
    };

    private static final String[] QUEST_COMPLETE = {
        "§a[Житель] §fСпасибо огромное! Вот ваша награда!",
        "§a[Житель] §fПревосходная работа! Деревня вам благодарна!",
        "§a[Житель] §fВы настоящий герой нашей деревни!",
    };

    private static final String[] QUEST_WAIT = {
        "§a[Житель] §7Задание ещё не выполнено... Жду вас!",
        "§a[Житель] §7Поторопитесь, пожалуйста!",
    };

    public QuestVillagerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createQuestVillagerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new FleeEntityGoal<>(this,
                net.minecraft.entity.mob.ZombieEntity.class, 8.0f, 0.6, 0.6));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.5));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (this.getWorld().isClient) return ActionResult.SUCCESS;

        // Ищем свиток задания в инвентаре
        ItemStack questScroll = ItemStack.EMPTY;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() == ModItems.QUEST_SCROLL) {
                questScroll = stack;
                break;
            }
        }

        if (!questScroll.isEmpty() && questScroll.hasNbt()) {
            NbtCompound nbt = questScroll.getNbt();
            boolean complete = nbt.getBoolean("QuestComplete");

            if (complete) {
                // Задание выполнено — выдаём награду
                String msg = QUEST_COMPLETE[(int)(Math.random() * QUEST_COMPLETE.length)];
                player.sendMessage(Text.literal(msg), false);
                QuestManager.grantQuestReward(player);
                // Убираем свиток
                questScroll.decrement(1);
                this.questGiven = false;
            } else {
                // Проверяем выполнение
                if (QuestManager.checkQuestCompletion(player, questScroll)) {
                    nbt.putBoolean("QuestComplete", true);
                    questScroll.setNbt(nbt);
                    player.sendMessage(Text.literal("§a[Житель] §fЗадание выполнено! Принесите свиток обратно."), false);
                } else {
                    // Ещё не выполнено
                    String msg = QUEST_WAIT[(int)(Math.random() * QUEST_WAIT.length)];
                    player.sendMessage(Text.literal(msg), false);
                    // Показываем цель задания
                    player.sendMessage(Text.literal("§7Цель: " + nbt.getString("QuestDescription")), false);
                }
            }
        } else {
            // Выдаём задание
            String greeting = QUEST_GREETINGS[(int)(Math.random() * QUEST_GREETINGS.length)];
            player.sendMessage(Text.literal(greeting), false);

            if (!this.questGiven) {
                // Создаём свиток и выдаём
                QuestManager.openQuestMenu(player, this.getWorld(), this.getBlockPos());
                this.questGiven = true;
            } else {
                player.sendMessage(Text.literal("§a[Житель] §7Я уже дал вам задание. Выполните его!"), false);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }
}
