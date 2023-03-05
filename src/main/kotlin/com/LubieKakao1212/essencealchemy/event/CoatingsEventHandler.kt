package example.examplemod.event

import com.LubieKakao1212.essencealchemy.capability.EssAlcCapabilities
import net.minecraft.nbt.Tag
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.ForgeRegistries

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
}