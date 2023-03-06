package com.LubieKakao1212.essencealchemy.capability.coating

import com.LubieKakao1212.essencealchemy.coating.Coating

interface ICoatingInstance {

    val isCoated : Boolean

    val coating : Coating
    val usesLeft : Int

    /**
     * @return does this coating have any uses left
     */
    fun use() :  Boolean

    fun apply(coating: Coating, uses : Int) : Boolean

}