package com.LubieKakao1212.essencealchemy.util

import net.minecraft.nbt.IntTag
import net.minecraftforge.common.util.INBTSerializable

/**
 * Represents a color
 */
class Color(val r : Byte, val g : Byte, val b : Byte, val a : Byte) : INBTSerializable<IntTag> {

    companion object {

        val black = fromRGB(0f, 0f, 0f, 1f)
        val blackClear = fromRGB(0f, 0f, 0f, 0f)
        val white = fromRGB(1f, 1f, 1f, 1f)
        val whiteClear = fromRGB(1f, 1f, 1f, 0f)

        /**
         * byte order from most to least significant
         * a b g r
         */
        fun fromEncoded(encoded : Int) : Color {
            var enc = encoded;
            val b = enc.toByte()
            enc = enc.ushr(8)
            val g = enc.toByte()
            enc = enc.ushr(8)
            val r = enc.toByte()
            enc = enc.ushr(8)
            val a = enc.toByte()
            return Color(r, g, b, a)
        }

        fun fromRGB(r : Float, g : Float, b : Float, a : Float) : Color {
            val r1 = (r * 255).toInt().toByte()
            val g1 = (g * 255).toInt().toByte()
            val b1 = (b * 255).toInt().toByte()
            val a1 = (a * 255).toInt().toByte()
            return Color(r1, g1, b1, a1)
        }
    }

    /**
     * byte order from most to least significant
     * a b g r
     */
    fun encode() : Int {
        var out : Int = (a.toInt() + 256) shl 8;
        out = (out or r.toInt() + 256) shl 8;
        out = (out or g.toInt() + 256) shl 8;
        out = (out or b.toInt() + 256);

        return out;
    }

    /**
     *  Decodes the color to this instance
     */
    fun decode(encoded : Int) {
        var enc = encoded;
        val b = enc.toByte()
        enc = enc.ushr(8)
        val g = enc.toByte()
        enc = enc.ushr(8)
        val r = enc.toByte()
        enc = enc.ushr(8)
        val a = enc.toByte()
    }

    override fun serializeNBT(): IntTag {
        return IntTag.valueOf(encode());
    }

    override fun deserializeNBT(nbt: IntTag?) {
        nbt?.let {
            decode(it.asInt)
        }
    }

}