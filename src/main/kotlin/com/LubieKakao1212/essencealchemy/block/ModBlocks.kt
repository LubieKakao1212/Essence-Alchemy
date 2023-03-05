package com.LubieKakao1212.essencealchemy.block

import com.LubieKakao1212.essencealchemy.EssenceAlchemy
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModBlocks {
    val REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, EssenceAlchemy.ID)

    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times
    val EXAMPLE_BLOCK by REGISTER.registerObject("example_block") {
        Block(BlockBehaviour.Properties.of(Material.BAMBOO).lightLevel { 15 }.strength(3.0f))
    }
}