package com.LubieKakao1212.essencealchemy.register

import com.LubieKakao1212.essencealchemy.EssenceAlchemy
import com.LubieKakao1212.essencealchemy.coating.Coating
import com.LubieKakao1212.essencealchemy.coating.MobEffectCoating
import com.LubieKakao1212.essencealchemy.coating.NoCoating
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryBuilder
import com.LubieKakao1212.essencealchemy.util.*
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import thedarkcolour.kotlinforforge.forge.registerObject

object Coatings {

    val REGISTER = DeferredRegister.create(Coating::class.java, EssenceAlchemy.ID)

    val COATINGS_REGISTRY by REGISTER.registerRegistry("coatings") { RegistryBuilder() }

    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times
    val NO_COATING by REGISTER.registerObject("none") {
        NoCoating()
    }

    val POISON_I by REGISTER.registerObject("poison_1") {
        MobEffectCoating(MobEffectInstance(MobEffects.POISON, 60, 0))
    }

    val POISON_II by REGISTER.registerObject("poison_2") {
        MobEffectCoating(MobEffectInstance(MobEffects.POISON, 40, 1))
    }

    val DECAY_I by REGISTER.registerObject("decay_1") {
        MobEffectCoating(MobEffectInstance(MobEffects.POISON, 60, 0))
        MobEffectCoating(MobEffectInstance(MobEffects.WITHER, 40, 0))
    }

    val MAGIC_I by REGISTER.registerObject("magic_1") {
        MobEffectCoating(MobEffectInstance(MobEffects.HARM, 1, 0))
    }

}