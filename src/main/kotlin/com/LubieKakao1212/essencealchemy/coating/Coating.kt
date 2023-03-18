package com.LubieKakao1212.essencealchemy.coating

import com.LubieKakao1212.essencealchemy.capability.coating.IAppliedCoatingInstance
import com.LubieKakao1212.essencealchemy.util.Color
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.registries.ForgeRegistryEntry

abstract class Coating(properties : Properties) : ForgeRegistryEntry<Coating>() {

    private val color : Color = properties.color

    open fun hitEntity(coating : CoatingInstance, attacker : LivingEntity, target : LivingEntity) {

    }

    open fun getBlockBreakSpeedModifier(coatedItem : CoatingInstance, breaker : LivingEntity, blockPos : BlockPos, block : BlockState) : Double { return 1.0 }

    open fun canMerge(other : Coating) : Boolean { return other === this }

    /**
     * Called before default changes are made in {@link ICoatingInstance# a }
     * If false is returned default changes will not be applied
     * @param thisIn this coating instance
     * @param otherIn
     * @return
     */
    open fun apply(thisIn : CoatingInstance, otherIn : CoatingInstance) : Boolean { return true }

    open fun getCoatingName(coating : CoatingInstance) : MutableComponent {
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