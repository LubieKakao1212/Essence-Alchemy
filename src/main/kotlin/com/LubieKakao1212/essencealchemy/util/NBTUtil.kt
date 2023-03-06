package com.LubieKakao1212.essencealchemy.util

import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

fun CompoundTag.getResourceLocation(key: String) : ResourceLocation? {
    return ResourceLocation.tryParse(this.getString(key))
}

fun <T : IForgeRegistryEntry<T>> CompoundTag.getRegistryEntry(key: String, registry : IForgeRegistry<T>) : T? {
    return registry.getValue(this.getResourceLocation(key))
}