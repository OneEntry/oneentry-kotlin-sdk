package com.example.oneentry.model

import com.example.oneentry.network.OneEntryProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

/**
 * OneEntry Product Status
 *
 * @param id Product status id
 * @param updatedDate Product status update date
 * @param version Product status version number
 * @param identifier Product status marker
 * @param localizeInfos Product status marker localize info
 */
@Serializable
data class ProductStatus(
    val id: Int,
    val updatedDate: String,
    val version: Int,
    val identifier: String,
    val localizeInfos: Map<String, LocalizeInfo>
)

