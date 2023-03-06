package com.LubieKakao1212.essencealchemy.event

import com.LubieKakao1212.essencealchemy.capability.EssAlcCapabilities
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber
object CoatingsEventHandler {

    @SubscribeEvent
    fun livingAttack(event : LivingAttackEvent) {
        event.source.entity?.let {sourceEntity ->
            if(sourceEntity is LivingEntity)
            {
                var item = sourceEntity.mainHandItem
                if(item.isEmpty) {
                    item = sourceEntity.offhandItem
                    if(item.isEmpty)
                        return
                }
                item.getCapability(EssAlcCapabilities.COATING).ifPresent {coating ->
                    if(coating.isCoated) {
                        coating.coating.hitEntity(coating, sourceEntity, event.entityLiving)
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun itemTooltip(event : ItemTooltipEvent) {
        event.itemStack.getCapability(EssAlcCapabilities.COATING).ifPresent {coating ->
            if(coating.isCoated)
                event.toolTip.add(coating.coating.getCoatingName(coating).append(" ${coating.usesLeft}"))
        }
    }
}