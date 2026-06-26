package com.enhancedvillages.quest;

import com.enhancedvillages.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class QuestManager {

    // Типы заданий
    public enum QuestType {
        GATHER,    // Собрать ресурсы
        SLAY,      // Убить монстров
        DELIVER,   // Доставить товар
        EXPLORE    // Исследовать местность
    }

    // Описания заданий
    private static final String[][] QUESTS = {
        // {название, описание, тип, цель}
        {"Охота на зомби", "Убейте 10 зомби, угрожающих деревне", "SLAY", "ZOMBIE:10"},
        {"Сбор пшеницы", "Принесите 32 единицы пшеницы для таверны", "GATHER", "WHEAT:32"},
        {"Доставка почты", "Передайте письмо в соседнюю деревню", "DELIVER", "PAPER:1"},
        {"Поиск железа", "Добудьте 16 железных слитков для кузнеца", "GATHER", "IRON_INGOT:16"},
        {"Охота на скелетов", "Убейте 8 скелетов для защиты стад", "SLAY", "SKELETON:8"},
        {"Сбор дерева", "Принесите 64 единицы брёвен для строительства", "GATHER", "OAK_LOG:64"},
        {"Поиск алмазов", "Найдите 4 алмаза для ювелира", "GATHER", "DIAMOND:4"},
        {"Устранение рейдеров", "Убейте 5 разбойников", "SLAY", "PILLAGER:5"},
        {"Исследование пещер", "Исследуйте ближайшую пещеру", "EXPLORE", "CAVE:1"},
        {"Доставка еды", "Принесите 20 единиц хлеба голодающим", "DELIVER", "BREAD:20"},
    };

    public static void openQuestMenu(PlayerEntity player, World world, BlockPos boardPos) {
        // Выбираем случайное задание
        Random random = new Random(world.getTime() / 6000 + boardPos.hashCode());
        int questIdx = random.nextInt(QUESTS.length);
        String[] quest = QUESTS[questIdx];

        // Проверяем, есть ли уже свиток у игрока
        boolean hasQuest = false;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() == ModItems.QUEST_SCROLL) {
                hasQuest = true;
                break;
            }
        }

        if (hasQuest) {
            player.sendMessage(Text.literal("§c[Доска заданий] §4У вас уже есть активное задание!"), false);
            return;
        }

        // Создаём свиток задания
        ItemStack questScroll = new ItemStack(ModItems.QUEST_SCROLL);
        NbtCompound nbt = new NbtCompound();
        nbt.putString("QuestName", quest[0]);
        nbt.putString("QuestDescription", quest[1]);
        nbt.putString("QuestType", quest[2]);
        nbt.putString("QuestTarget", quest[3]);
        nbt.putBoolean("QuestComplete", false);
        questScroll.setNbt(nbt);
        questScroll.setCustomName(Text.literal("§2Задание: §a" + quest[0]));

        // Выдаём игроку
        if (!player.giveItemStack(questScroll)) {
            player.dropItem(questScroll, false);
        }

        player.sendMessage(Text.literal("§2[Доска заданий] §aВы взяли задание: §e" + quest[0]), false);
        player.sendMessage(Text.literal("§7" + quest[1]), false);
        player.sendMessage(Text.literal("§6Награда: 5 монет деревни + 100 опыта"), false);
    }

    /**
     * Проверить выполнение задания (вызывается при взаимодействии с квестмастером)
     */
    public static boolean checkQuestCompletion(PlayerEntity player, ItemStack questScroll) {
        if (!questScroll.hasNbt()) return false;
        NbtCompound nbt = questScroll.getNbt();

        String target = nbt.getString("QuestTarget");
        String[] parts = target.split(":");
        if (parts.length < 2) return false;

        String targetItem = parts[0];
        int targetCount = Integer.parseInt(parts[1]);

        // Считаем предмет в инвентаре игрока (для GATHER/DELIVER заданий)
        String questType = nbt.getString("QuestType");
        if (questType.equals("GATHER") || questType.equals("DELIVER")) {
            int count = 0;
            for (int i = 0; i < player.getInventory().size(); i++) {
                ItemStack stack = player.getInventory().getStack(i);
                if (stack.getItem().toString().toUpperCase().contains(targetItem)) {
                    count += stack.getCount();
                }
            }
            return count >= targetCount;
        }

        // Для SLAY и EXPLORE — считаем через статистику (упрощено: всегда принимаем)
        return true;
    }

    /**
     * Выдать награду за выполненное задание
     */
    public static void grantQuestReward(PlayerEntity player) {
        // 5 монет деревни
        ItemStack coins = new ItemStack(ModItems.VILLAGE_COIN, 5);
        if (!player.giveItemStack(coins)) {
            player.dropItem(coins, false);
        }

        // 100 единиц опыта
        player.addExperience(100);

        player.sendMessage(Text.literal("§a✔ Задание выполнено! §6+5 монет деревни §a+100 опыта"), false);
    }
}
