package com.example.oneentry.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
@Serializable
enum class ConditionMarker {

    @SerialName("in")
    IN,
    @SerialName("nin")
    NIN,
    @SerialName("eq")
    EQ,
    @SerialName("neq")
    NEQ,
    @SerialName("mth")
    MTH,
    @SerialName("lth")
    LTH,
    @SerialName("exs")
    EXS,
    @SerialName("nexs")
    NEXS
}
