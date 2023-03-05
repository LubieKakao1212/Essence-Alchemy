package com.LubieKakao1212.essencealchemy.capability

import com.LubieKakao1212.essencealchemy.capability.coating.ICoatingInstance
import net.minecraftforge.common.capabilities.CapabilityDispatcher
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityProvider
import net.minecraftforge.common.capabilities.CapabilityToken

object EssAlcCapabilities {

    val COATING = CapabilityManager.get(object: CapabilityToken<ICoatingInstance>() { })

}