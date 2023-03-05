package com.LubieKakao1212.essencealchemy.event

import com.LubieKakao1212.essencealchemy.EssenceAlchemy
import com.LubieKakao1212.essencealchemy.capability.provider.ItemCoatingProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber
object AttachCapabilityHandler {

    @SubscribeEvent
    fun attachItem(event : AttachCapabilitiesEvent<ItemStack>) {
        event.addCapability(ResourceLocation(EssenceAlchemy.ID, "coating"), ItemCoatingProvider(event.`object`))
    }

}