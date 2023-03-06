package com.LubieKakao1212.essencealchemy.capability.coating

import com.LubieKakao1212.essencealchemy.coating.Coating
import com.LubieKakao1212.essencealchemy.register.Coatings
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack

class ItemCoatingInstance(private val item : ItemStack, coatingIn : Coating, uses : Int) : ICoatingInstance {

    override val isCoated: Boolean
        get() { return (usesLeft > 0) or (coating === Coatings.NO_COATING) }
    override var usesLeft: Int = uses
        set(value) {
            modifyCoatingTag(item) {
                it.putInt("uses", value)
            }
            field = value
        }
    override var coating: Coating = coatingIn
        private set(value) {
            modifyCoatingTag(item) {
                it.putString("coating", coating.registryName?.toString() ?: Coatings.NO_COATING.registryName!!.toString())
            }
            field = value
        }

    override fun use() : Boolean {
        if((--usesLeft) <= 0)
        {
            coating = Coatings.NO_COATING
            return false;
        }
        return true;
    }

    override fun apply(coatingIn: Coating, uses: Int): Boolean {
        if(coating.canMerge(coatingIn)) {
            coating = coating.merge(coatingIn)
            usesLeft += uses
            return true
        }
        return false
    }

    fun clear() {
        usesLeft = 0
        coating = Coatings.NO_COATING
    }

    fun mergeWith(other : ItemCoatingInstance) : Boolean {
        if(coating.canMerge(other.coating)) {
            this.usesLeft += other.usesLeft
            return true
        }
        return false
    }

    private fun modifyCoatingTag(stack : ItemStack, action : (CompoundTag) -> Unit) {
        val tag = stack.tag ?: CompoundTag()
        val coatingTag = tag.getCompound("coating")
        action(coatingTag)
        tag.put("coating", coatingTag)
        stack.tag = tag
    }

}