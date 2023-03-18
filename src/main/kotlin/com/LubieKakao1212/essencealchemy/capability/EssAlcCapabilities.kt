package com.LubieKakao1212.essencealchemy.capability

import com.LubieKakao1212.essencealchemy.capability.coating.IAppliedCoatingInstance
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken

object EssAlcCapabilities {

    val COATING = CapabilityManager.get(object: CapabilityToken<IAppliedCoatingInstance>() { })

}