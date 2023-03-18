package com.LubieKakao1212.essencealchemy.capability.coating

import com.LubieKakao1212.essencealchemy.coating.Coating
import com.LubieKakao1212.essencealchemy.coating.CoatingInstance
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack

class ItemCoatingInstance(private val item : ItemStack) : IAppliedCoatingInstance {

    override var coating: CoatingInstance = CoatingInstance.createForStack(item)

    override val isCoated: Boolean = coating.isCoated

    init {
        coating.onChanged += ::applyItemTag;
    }

    override fun use() : Boolean {
        return coating.use()
    }

    override fun apply(coatingIn : CoatingInstance): Boolean {
        return coating.apply(coatingIn)
    }

    private fun applyItemTag(coating : CoatingInstance) {
        item.tag = item.tag ?: CompoundTag()

        item.tag?.put(CoatingInstance.coatingTagKey, coating.serializeNBT())
    }


}