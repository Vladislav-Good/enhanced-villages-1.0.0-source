package com.enhancedvillages;

import com.enhancedvillages.profession.ModProfessions;
import com.enhancedvillages.trade.ModTradeOffers;
import com.enhancedvillages.world.ModStructures;
import com.enhancedvillages.entity.ModEntities;
import com.enhancedvillages.item.ModItems;
import com.enhancedvillages.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhancedVillages implements ModInitializer {

    public static final String MOD_ID = "enhancedvillages";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Enhanced Villages mod initializing...");

        ModBlocks.register();
        ModItems.register();
        ModEntities.register();
        ModProfessions.register();
        ModTradeOffers.register();
        ModStructures.register();

        LOGGER.info("Enhanced Villages mod loaded successfully!");
    }
}
