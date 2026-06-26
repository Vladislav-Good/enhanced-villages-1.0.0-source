package com.enhancedvillages.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

// Торговый контракт
class TradeContractItem extends Item {
    public TradeContractItem(Settings settings) { super(settings); }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Официальный договор с деревней").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("Предъявите торговцу для скидки 20%").formatted(Formatting.GRAY));
    }
}

// Монета деревни
class VillageCoinItem extends Item {
    public VillageCoinItem(Settings settings) { super(settings); }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Валюта деревни").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("Принимается на рынке").formatted(Formatting.GRAY));
    }
}

// Карта каравана
class CaravanMapItem extends Item {
    public CaravanMapItem(Settings settings) { super(settings); }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Маршрут торгового каравана").formatted(Formatting.AQUA));
        tooltip.add(Text.literal("Следуйте красным стрелкам").formatted(Formatting.GRAY));
    }

    @Override
    public ItemStack use(World world, PlayerEntity user, net.minecraft.util.Hand hand) {
        if (!world.isClient) {
            user.sendMessage(Text.literal("§b[Карта Каравана] §3Следующая остановка каравана: на севере!"), false);
        }
        return super.use(world, user, hand);
    }
}

// Медальон стражника
class GuardMedallionItem extends Item {
    public GuardMedallionItem(Settings settings) { super(settings); }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Знак уважения жителей деревни").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.literal("Монстры реже нападают рядом с деревней").formatted(Formatting.GRAY));
    }
}

// Свиток задания
class QuestScrollItem extends Item {
    public QuestScrollItem(Settings settings) { super(settings); }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Задание жителя деревни").formatted(Formatting.GREEN));
        tooltip.add(Text.literal("ПКМ для просмотра деталей").formatted(Formatting.GRAY));
        if (stack.hasNbt() && stack.getNbt().contains("QuestDescription")) {
            tooltip.add(Text.literal("§7" + stack.getNbt().getString("QuestDescription")));
        }
    }

    @Override
    public ItemStack use(World world, PlayerEntity user, net.minecraft.util.Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            String desc = (stack.hasNbt() && stack.getNbt().contains("QuestDescription"))
                    ? stack.getNbt().getString("QuestDescription")
                    : "Задание ещё не заполнено";
            user.sendMessage(Text.literal("§2[Задание] §a" + desc), false);
        }
        return stack;
    }
}
