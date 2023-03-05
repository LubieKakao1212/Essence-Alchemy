package com.LubieKakao1212.essencealchemy.coating

import com.LubieKakao1212.essencealchemy.capability.coating.ICoatingInstance
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.registries.ForgeRegistryEntry

abstract class Coating : ForgeRegistryEntry<Coating>() {

    open fun hitEntity(coating : ICoatingInstance, attacker : LivingEntity, target : LivingEntity) {

    }

    open fun getBlockBreakSpeedModifier(coatedItem : ICoatingInstance, breaker : LivingEntity, blockPos : BlockPos, block : BlockState) : Double { return 1.0 }

    open fun canMerge(other : Coating) : Boolean {
        return other === this;
    }
}