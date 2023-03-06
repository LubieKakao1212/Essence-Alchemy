package com.LubieKakao1212.essencealchemy.commands

import com.LubieKakao1212.essencealchemy.capability.EssAlcCapabilities
import com.LubieKakao1212.essencealchemy.commands.argument.RegistryArgument
import com.LubieKakao1212.essencealchemy.register.Coatings
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.TextComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
object EssenceCommand {

    fun register(dispatcher : CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("essence").then(
                Commands.literal("coat").then(
                    Commands.argument("coating", RegistryArgument.keyArg(Coatings.COATINGS_REGISTRY)).then(
                        Commands.argument("uses", IntegerArgumentType.integer(0)).executes { ctx ->
                            coatItem(
                                ctx.source.playerOrException,
                                RegistryArgument.key(ctx, "coating"),
                                IntegerArgumentType.getInteger(ctx, "uses")
                            )
                            1
                        }
                    )
                )
            )
        )
    }

    private fun coatItem(player : Player, effectId : ResourceLocation, uses : Int) {
        val stack = player.getItemInHand(InteractionHand.MAIN_HAND).copy()

        stack.getCapability(EssAlcCapabilities.COATING).ifPresent {cap ->
            Coatings.COATINGS_REGISTRY.getValue(effectId)?.let {coating ->
                if(cap.apply(coating, uses))
                {
                    player.sendMessage(TextComponent("Success").withStyle(ChatFormatting.GREEN), player.uuid)
                    return@ifPresent
                }
                player.sendMessage(TextComponent("Coating mismatch").withStyle(ChatFormatting.RED), player.uuid)
                return@ifPresent
            }
            player.sendMessage(TextComponent("Invalid id").withStyle(ChatFormatting.RED), player.uuid)
        }

        //TODO use Copy ?
        player.setItemInHand(InteractionHand.MAIN_HAND, stack)
    }
}