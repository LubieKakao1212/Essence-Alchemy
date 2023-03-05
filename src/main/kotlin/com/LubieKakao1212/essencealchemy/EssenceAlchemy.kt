package com.LubieKakao1212.essencealchemy

import com.LubieKakao1212.essencealchemy.block.ModBlocks
import com.LubieKakao1212.essencealchemy.register.Coatings
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(EssenceAlchemy.ID)
object EssenceAlchemy {
    const val ID = "essencealchemy"

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        ModBlocks.REGISTER.register(MOD_BUS)
        Coatings.REGISTER.register(MOD_BUS)
    }
}