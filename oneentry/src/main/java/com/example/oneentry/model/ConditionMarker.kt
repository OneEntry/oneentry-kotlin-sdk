package com.example.oneentry.model

import com.google.gson.annotations.SerializedName

/**
 * OneEntry conditional operators
 *
 * @param in Marker for the operator "included in the list"
 * @param nin Marker for the operator "not included in the list"
 * @param eq Marker for the "equal" operator
 * @param neq Marker for the operator "not equal to"
 * @param mth Marker for the "more" operator
 * @param lth Marker for the "less" operator
 * @param exs Marker for the operator "exists"
 * @param nexs Marker for the operator "does not exist"
 */
enum class ConditionMarker {
    `in`,
    nin,
    eq,
    neq,
    mth,
    lth,
    exs,
    nexs
}
