package com.LubieKakao1212.essencealchemy.coating

import com.LubieKakao1212.essencealchemy.capability.coating.ICoatingInstance
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

class MobEffectCoating(private vararg val effects : MobEffectInstance) : Coating() {

    override fun hitEntity(coating: ICoatingInstance, attacker: LivingEntity, target: LivingEntity) {
        if(coating.isCoated)
        {
            coating.use()
            for(effect in effects)
            {
                target.addEffect(MobEffectInstance(effect))
            }
        }
    }
}