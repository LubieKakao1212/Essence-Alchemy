package com.LubieKakao1212.essencealchemy.coating

class NoCoating : Coating(Properties())
{
    override fun canMerge(other: Coating): Boolean {
        return true
    }

    /**
     * @return what coating is the result of this merge
     */
    override fun merge(other: Coating): Coating {
        return other
    }
}