package com.LubieKakao1212.essencealchemy.register

import com.LubieKakao1212.essencealchemy.EssenceAlchemy
import com.LubieKakao1212.essencealchemy.coating.Coating
import com.LubieKakao1212.essencealchemy.coating.NoCoating
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryBuilder
import com.LubieKakao1212.essencealchemy.util.*
import thedarkcolour.kotlinforforge.forge.registerObject

object Coatings {

    val REGISTER = DeferredRegister.create(Coating::class.java, EssenceAlchemy.ID)

    val COATINGS_REGISTRY by REGISTER.registerRegistry("coatings") { RegistryBuilder() }

    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times
    val NO_COATING by REGISTER.registerObject("none") {
        NoCoating()
    }


}