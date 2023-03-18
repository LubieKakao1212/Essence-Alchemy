package com.LubieKakao1212.essencealchemy.coating

import com.LubieKakao1212.essencealchemy.coating.Coating
import com.LubieKakao1212.essencealchemy.register.Coatings
import com.LubieKakao1212.essencealchemy.util.Event
import com.LubieKakao1212.essencealchemy.util.getRegistryEntry
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.util.INBTSerializable
import javax.xml.crypto.Data

class CoatingInstance(coatingIn : Coating, usesIn : Int, intensityIn: Double = 1.0) : INBTSerializable<CompoundTag> {

    companion object {

        val EMPTY : CoatingInstance
            get() {
                return CoatingInstance(Coatings.NO_COATING, 0, 0.0)
            }

        val coatingTagKey = "coating"
        val coatingIdTagKey = "coating"
        val usesTagKey = "uses"
        val intensityTagKey = "intensity"
        val dataTagKey = "data"

        fun createForStack(stack : ItemStack) : CoatingInstance {
            return stack.tag?.getCompound(coatingTagKey).let { tag -> val c = EMPTY; c.deserializeNBT(tag); c}
        }
    }

    val isCoated: Boolean
        get() { return usesLeft > 0 && intensity > 0.0 && coating !== Coatings.NO_COATING }

    var coating: Coating = coatingIn
        private set

    var usesLeft: Int = usesIn

    var intensity : Double = intensityIn

    val onChanged : Event<CoatingInstance> = Event();

    var tag : Tag = CompoundTag()
        private set

    fun apply(coatingIn : CoatingInstance): Boolean {
        if(coatingIn.isCoated && coating.apply(this, coatingIn)) {
            if(isCoated) {
                intensity =
                    ((intensity * usesLeft) + (coatingIn.intensity * coatingIn.usesLeft)) / (usesLeft + coatingIn.usesLeft)
                usesLeft += coatingIn.usesLeft
            }
            else {
                coating = coatingIn.coating
                usesLeft = coatingIn.usesLeft
                intensity = coatingIn.intensity
                tag = coatingIn.tag
            }
            markDirty()
            return true
        }
        return false
    }

    fun clear() {
        usesLeft = 0
        intensity = 0.0
        coating = Coatings.NO_COATING
        tag = CompoundTag()
    }

    fun markDirty() {
        onChanged.invoke(this)
    }

    private fun modifyCoatingTag(stack : ItemStack, action : (CompoundTag) -> Unit) {
        val tag = stack.tag ?: CompoundTag()
        val coatingTag = tag.getCompound("coating")
        action(coatingTag)
        tag.put("coating", coatingTag)
        stack.tag = tag
    }

    override fun serializeNBT(): CompoundTag {
        val tag = CompoundTag()

        tag.putString(coatingIdTagKey, coating.registryName?.toString() ?: Coatings.NO_COATING.registryName!!.toString())
        tag.putInt(usesTagKey, usesLeft)
        tag.putDouble(intensityTagKey, intensity)
        tag.put(dataTagKey, this.tag.copy())

        return tag
    }

    override fun deserializeNBT(nbt: CompoundTag?) {
        nbt?.let {tag ->
            val coating = tag.getRegistryEntry(coatingIdTagKey, Coatings.COATINGS_REGISTRY) ?: Coatings.NO_COATING
            val uses = tag.getInt(usesTagKey)
            val intensity = tag.getDouble(intensityTagKey)
            val data = tag.getCompound(dataTagKey)

            if(uses > 0 && intensity > 0 && (coating !== Coatings.NO_COATING)) {
                this.coating = coating
                this.usesLeft = uses
                this.intensity = intensity
                this.tag = data.copy()
            }
            else
            {
                clear()
            }
            markDirty()
        }
    }

}