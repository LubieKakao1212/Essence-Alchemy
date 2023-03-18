package com.LubieKakao1212.essencealchemy.coating

import com.LubieKakao1212.essencealchemy.capability.coating.IAppliedCoatingInstance
import com.LubieKakao1212.essencealchemy.util.linear
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import kotlin.math.floor

class MobEffectCoating(properties : Properties, private vararg val effects : EffectInstance) : Coating(properties) {

    companion object {
        fun effect(effect : MobEffect, duration : Int, amplifier : (Double) -> Double = linear(1.0)) : EffectInstance {
            return EffectInstance(effect, duration, amplifier)
        }
    }

    override fun hitEntity(coating: CoatingInstance, attacker: LivingEntity, target: LivingEntity) {
        if (coating.isCoated) {
            for (effect in effects) {
                val ampl = floor(effect.amplifier(coating.intensity))
                if (ampl > 0) {
                    target.addEffect(MobEffectInstance(effect.effect, effect.duration, ampl.toInt()))
                }
            }
            coating.use()
            coating.markDirty()
        }
    }

    class EffectInstance(val effect : MobEffect, val duration : Int, val amplifier : (Double) -> Double)
}
