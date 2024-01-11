package com.example.oneentry.model

import kotlinx.serialization.Serializable

/**
 * OneEntry template model
 *
 * @param id Template id
 * @param identifier Template marker
 * @param generalTypeId Template general type id
 * @param generalTypeName Template general type name
 * @param position Template position
 * @param localizeInfos Template localize info
 */
@Serializable
data class OneEntryTemplate(
    val id: Int,
    val identifier: String,
    val generalTypeId: Int,
    val generalTypeName: String,
    val position: Int?,
    val localizeInfos: Map<String, LocalizeInfo>?
)

/**
 * OneEntry templates
 */
@Serializable
data class OneEntryAllTemplates(
    val asDictionary: Map<String, List<OneEntryTemplate>>
) {
    val forCatalogProducts: List<OneEntryTemplate>
        get() = asDictionary["forCatalogProducts"] ?: emptyList()
    val forBasketPage: List<OneEntryTemplate>
        get() = asDictionary["forBasketPage"] ?: emptyList()
    val forErrorPage: List<OneEntryTemplate>
        get() = asDictionary["forErrorPage"] ?: emptyList()
    val forCatalogPages: List<OneEntryTemplate>
        get() = asDictionary["forCatalogPages"] ?: emptyList()
    val forProductPreview: List<OneEntryTemplate>
        get() = asDictionary["forProductPreview"] ?: emptyList()
    val forProductPage: List<OneEntryTemplate>
        get() = asDictionary["forProductPage"] ?: emptyList()
    val forSimilarProductBlock: List<OneEntryTemplate>
        get() = asDictionary["forSimilarProductBlock"] ?: emptyList()
    val forStatisticProductBlock: List<OneEntryTemplate>
        get() = asDictionary["forStatisticProductBlock"] ?: emptyList()
    val forProductBlock: List<OneEntryTemplate>
        get() = asDictionary["forProductBlock"] ?: emptyList()
    val forForm: List<OneEntryTemplate>
        get() = asDictionary["forForm"] ?: emptyList()
    val forFormField: List<OneEntryTemplate>
        get() = asDictionary["forFormField"] ?: emptyList()
    val forNewsPage: List<OneEntryTemplate>
        get() = asDictionary["forNewsPage"] ?: emptyList()
    val forNewsBlock: List<OneEntryTemplate>
        get() = asDictionary["forNewsBlock"] ?: emptyList()
    val forNewsPreview: List<OneEntryTemplate>
        get() = asDictionary["forNewsPreview"] ?: emptyList()
    val forOneNewsPage: List<OneEntryTemplate>
        get() = asDictionary["forOneNewsPage"] ?: emptyList()
    val forUsualPage: List<OneEntryTemplate>
        get() = asDictionary["forUsualPage"] ?: emptyList()
    val forTextBlock: List<OneEntryTemplate>
        get() = asDictionary["forTextBlock"] ?: emptyList()
    val forSliderBlock: List<OneEntryTemplate>
        get() = asDictionary["forSliderBlock"] ?: emptyList()
}