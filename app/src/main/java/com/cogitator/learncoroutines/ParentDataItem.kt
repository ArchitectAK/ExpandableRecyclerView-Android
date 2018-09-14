package com.cogitator.learncoroutines

import java.io.Serializable

/**
 * @author Ankit Kumar on 14/09/2018
 */
data class ParentDataItem(val parentName: String, val childDataItems: List<ChildDataItem>) : Serializable