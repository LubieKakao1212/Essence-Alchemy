package com.LubieKakao1212.essencealchemy.util

import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import net.minecraftforge.registries.RegistryBuilder
import java.util.function.Supplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <V : IForgeRegistryEntry<V>> DeferredRegister<V>.registerRegistry(
    name: String, noinline supplier: () -> RegistryBuilder<V>): ReadOnlyProperty<Any?, IForgeRegistry<V>> {
    val registry = this.makeRegistry(name, supplier)

    // note that this anonymous class inherits three types
    return object : ReadOnlyProperty<Any?, IForgeRegistry<V>>, Supplier<IForgeRegistry<V>>, () -> IForgeRegistry<V> {
        override fun get(): IForgeRegistry<V> = registry.get()

        override fun getValue(thisRef: Any?, property: KProperty<*>): IForgeRegistry<V> = get()

        override fun invoke(): IForgeRegistry<V> = get()
    }
}