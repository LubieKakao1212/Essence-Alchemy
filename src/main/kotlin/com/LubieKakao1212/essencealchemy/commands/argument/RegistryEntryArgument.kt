package com.LubieKakao1212.essencealchemy.commands.argument

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.commands.SharedSuggestionProvider
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.ForgeRegistryEntry
import net.minecraftforge.registries.IForgeRegistry
import java.util.concurrent.CompletableFuture

object RegistryArgument {

    fun keyArg(registry : IForgeRegistry<*>) : Key {
        return Key {
            registry
        }
    }

    fun key(ctx : CommandContext<*>, name : String) : ResourceLocation {
        return ctx.getArgument(name, ResourceLocation::class.java);
    }

    fun mobEffectArg() : Entry<MobEffect> {
        return Entry {
            ForgeRegistries.MOB_EFFECTS
        }
    }

    fun mobEffect(ctx : CommandContext<*>, name : String) : MobEffect {
        return ctx.getArgument(name, MobEffect::class.java);
    }

    val ERROR_NOT_IN_REGISTRY = DynamicCommandExceptionType(){
        TranslatableComponent("registry.entryNotPresent", it.toString())
    }

    class Key(val registry : () -> IForgeRegistry<*>) : ArgumentType<ResourceLocation>{

        override fun parse(reader: StringReader?): ResourceLocation? {
            val id = ResourceLocation.read(reader);
            if(!registry().containsKey(id)) throw ERROR_NOT_IN_REGISTRY.create(id)
            return id
        }

        override fun <S : Any?> listSuggestions(context: CommandContext<S>?, builder: SuggestionsBuilder?): CompletableFuture<Suggestions> {
            return SharedSuggestionProvider.suggestResource(registry().keys, builder);
        }
    }

    class Entry<T : ForgeRegistryEntry<T>>(val registry : () -> IForgeRegistry<T>) : ArgumentType<T> {

        override fun parse(reader: StringReader?): T? {
            val id = ResourceLocation.read(reader);
            return registry().getValue(id) ?: throw ERROR_NOT_IN_REGISTRY.create(id);
        }

        override fun <S : Any?> listSuggestions(context: CommandContext<S>?, builder: SuggestionsBuilder?): CompletableFuture<Suggestions> {
            return SharedSuggestionProvider.suggestResource(registry().keys, builder);
        }

    }
}



