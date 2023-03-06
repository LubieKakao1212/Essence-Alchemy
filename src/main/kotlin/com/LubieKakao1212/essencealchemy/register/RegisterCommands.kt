package com.LubieKakao1212.essencealchemy.register

import com.LubieKakao1212.essencealchemy.commands.EssenceCommand
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber
object RegisterCommands {

    @SubscribeEvent
    fun registerCommands(event : RegisterCommandsEvent) {
        EssenceCommand.register(event.dispatcher)
    }


}