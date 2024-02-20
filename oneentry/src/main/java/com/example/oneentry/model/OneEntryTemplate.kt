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
    var templateDictionary: Map<String, List<OneEntryTemplate>>
) {
    val forCatalogProducts: List<OneEntryTemplate>
        get() = templateDictionary["forCatalogProducts"] ?: emptyList()
    val forBasketPage: List<OneEntryTemplate>
        get() = templateDictionary["forBasketPage"] ?: emptyList()
    val forErrorPage: List<OneEntryTemplate>
        get() = templateDictionary["forErrorPage"] ?: emptyList()
    val forCatalogPages: List<OneEntryTemplate>
        get() = templateDictionary["forCatalogPages"] ?: emptyList()
    val forProductPreview: List<OneEntryTemplate>
        get() = templateDictionary["forProductPreview"] ?: emptyList()
    val forProductPage: List<OneEntryTemplate>
        get() = templateDictionary["forProductPage"] ?: emptyList()
    val forSimilarProductBlock: List<OneEntryTemplate>
        get() = templateDictionary["forSimilarProductBlock"] ?: emptyList()
    val forStatisticProductBlock: List<OneEntryTemplate>
        get() = templateDictionary["forStatisticProductBlock"] ?: emptyList()
    val forProductBlock: List<OneEntryTemplate>
        get() = templateDictionary["forProductBlock"] ?: emptyList()
    val forForm: List<OneEntryTemplate>
        get() = templateDictionary["forForm"] ?: emptyList()
    val forFormField: List<OneEntryTemplate>
        get() = templateDictionary["forFormField"] ?: emptyList()
    val forNewsPage: List<OneEntryTemplate>
        get() = templateDictionary["forNewsPage"] ?: emptyList()
    val forNewsBlock: List<OneEntryTemplate>
        get() = templateDictionary["forNewsBlock"] ?: emptyList()
    val forNewsPreview: List<OneEntryTemplate>
        get() = templateDictionary["forNewsPreview"] ?: emptyList()
    val forOneNewsPage: List<OneEntryTemplate>
        get() = templateDictionary["forOneNewsPage"] ?: emptyList()
    val forUsualPage: List<OneEntryTemplate>
        get() = templateDictionary["forUsualPage"] ?: emptyList()
    val forTextBlock: List<OneEntryTemplate>
        get() = templateDictionary["forTextBlock"] ?: emptyList()
    val forSliderBlock: List<OneEntryTemplate>
        get() = templateDictionary["forSliderBlock"] ?: emptyList()
}