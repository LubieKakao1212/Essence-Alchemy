package com.LubieKakao1212.essencealchemy.capability.coating

import com.LubieKakao1212.essencealchemy.coating.Coating
import com.LubieKakao1212.essencealchemy.coating.CoatingInstance

interface IAppliedCoatingInstance {

    val isCoated : Boolean

    val coating : CoatingInstance

    /**
     * Decreases uses left by one
     * @return does this coating have any uses left
     */
    fun use() :  Boolean

    fun apply(coatingIn : CoatingInstance) : Boolean

}