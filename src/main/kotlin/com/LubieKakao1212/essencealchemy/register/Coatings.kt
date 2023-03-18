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

    val POISON_I by REGISTER.registerObject("poison") {
        MobEffectCoating(
            Coating.Properties()
                .setColor(Color.fromRGB(0.2f, 0.6f, 0.2f, 1f)
                ),
            MobEffectCoating.effect(MobEffects.POISON, 60, linear(1.0)))
    }

    val DECAY_I by REGISTER.registerObject("decay") {
        MobEffectCoating(
            Coating.Properties()
                .setColor(Color.fromRGB(0.2f, 0.3f, 0f, 1f)
                ),
            MobEffectCoating.effect(MobEffects.POISON, 60, linear(1.0, -0.5)),
            MobEffectCoating.effect(MobEffects.WITHER, 60, linear(0.5, 0.5)))
    }

    val MAGIC_I by REGISTER.registerObject("magic") {
        MobEffectCoating(Coating.Properties()
                .setColor(Color.fromRGB(0.6f, 0.2f, 0f, 1f)
                ),
            MobEffectCoating.effect(MobEffects.HARM, 1, linear(1.0)))
    }

}