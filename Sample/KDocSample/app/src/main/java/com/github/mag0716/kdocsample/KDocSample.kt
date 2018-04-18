package com.github.mag0716.kdocsample

/**
 * class
 *
 * @property property property
 * @constructor primary constructor
 */
class KDocSample(val property: String) {

    /**
     * secondary constructor
     *
     * @param property property
     * @param value value
     */
    constructor(property: String, value: Int) : this(property) {}

    /**
     * property
     */
    var value: Int? = null

    /**
     * companion object
     */
    companion object {
        /**
         * companion property
         */
        val property = "property"
    }

    /**
     * method
     *
     * @param arg param
     * @return return
     */
    fun method(arg: String = "default") = "return value"
}
