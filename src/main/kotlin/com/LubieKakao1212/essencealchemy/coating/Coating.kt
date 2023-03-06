package com.LubieKakao1212.essencealchemy.coating

import com.LubieKakao1212.essencealchemy.capability.coating.ICoatingInstance
import com.LubieKakao1212.essencealchemy.util.Color
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.registries.ForgeRegistryEntry

abstract class Coating(properties : Properties) : ForgeRegistryEntry<Coating>() {

    private val color : Color = properties.color

    open fun hitEntity(coating : ICoatingInstance, attacker : LivingEntity, target : LivingEntity) {

    }

    open fun getBlockBreakSpeedModifier(coatedItem : ICoatingInstance, breaker : LivingEntity, blockPos : BlockPos, block : BlockState) : Double { return 1.0 }

    open fun canMerge(other : Coating) : Boolean { return other === this }

    /**
     * @return what coating is the result of this merge
     */
    open fun merge(other : Coating) : Coating { return this }

    open fun getCoatingName(coating : ICoatingInstance) : MutableComponent {
        return TranslatableComponent(Util.makeDescriptionId("coating", registryName)).withStyle(Style.EMPTY.withColor(color.encode()))
    }

    class Properties {
        var color : Color = Color.white

        fun setColor(colorIn : Color) : Properties {
            color = colorIn
            return this
        }
    }

}