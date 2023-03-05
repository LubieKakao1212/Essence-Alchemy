package com.LubieKakao1212.essencealchemy.coating

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.registries.ForgeRegistryEntry

abstract class Coating : ForgeRegistryEntry<Coating>() {

    fun hitEntity(coatedItem : ItemStack, attacker : LivingEntity, target : LivingEntity) {

    }

    fun getBlockBreakSpeedModifier(coatedItem : ItemStack, breaker : LivingEntity, blockPos : BlockPos, block : BlockState) : Double { return 1.0 }

    fun canMerge(other : Coating) : Boolean {
        return other === this;
    }
}