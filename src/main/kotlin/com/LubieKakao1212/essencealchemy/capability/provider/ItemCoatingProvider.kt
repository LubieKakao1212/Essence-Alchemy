package com.LubieKakao1212.essencealchemy.capability.provider

import com.LubieKakao1212.essencealchemy.capability.EssAlcCapabilities
import com.LubieKakao1212.essencealchemy.capability.coating.ItemCoatingInstance
import com.LubieKakao1212.essencealchemy.register.Coatings
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional

class ItemCoatingProvider(stack : ItemStack) : ICapabilityProvider {

    private val coating : LazyOptional<ItemCoatingInstance> = LazyOptional.of { ItemCoatingInstance.createForStack(stack) }

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        if(cap == EssAlcCapabilities.COATING) {
            return coating as LazyOptional<T>
        }
        return LazyOptional.empty()
    }

}