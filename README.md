# OneEntry Kotlin SDK



- [Add OneEntry SDK to your Kotlin project](#add-oneentry-sdk-to-your-kotlin-project)
  - [Step 1: Create OneEntry project](#step-1-create-oneentry-project)
  - [Step 2: Register your app with OneEntry (In Development)](#step-2-register-your-app-with-oneentry)
  - [Step 3: Add OneEntry SDK to your app](#step-3-add-oneentry-sdk-to-your-app)
- [Using OneEntry Kotlin SDK](#using-oneentry-kotlin-sdk)
  - [OneEntryProducts](#oneentryproducts)
    - [Receiving all products with pagination](#receiving-all-products-with-pagination)
    - [Getting all products uncategorized, with pagination](#getting-all-products-uncategorized-with-pagination)
    - [Getting all products in the category by its id, with pagination](#getting-all-products-in-the-category-by-its-id-with-pagination)
    - [Getting all products in the category by its url, with pagination](#getting-all-products-in-the-category-by-its-url-with-pagination)
    - [Getting related products for another product by its id](#getting-related-products-for-another-product-by-its-id)
    - [Getting a product by its id](#getting-a-product-by-its-id)
    - [Product filter](#product-filter)
    - [Quick search](#quick-search)
    - [Product statuses](#product-statuses)
      - [Model](#model)
      - [All statuses](#all-statuses)
      - [Status by id](#status-by-id)
      - [Status by marker](#status-by-marker)
      - [Status marker validation](#status-marker-validation)
  - [OneEntryPages](#oneentrypages)
    - 


## Using OneEntry Kotlin SDK

### OneEntryProducts

#### Receiving all products with pagination

| Parameter       | Description                                                           |
|-----------------|-----------------------------------------------------------------------|
| langCode        | Locale code, used only when searching with a filter                   |
| statusMarker    | Identifier of the product page status                                 |
| conditionValue  | Value that is being searched                                          |
| conditionMarker | Filter condition identifier by which values are filtered              |
| attributeMarker | Text identifier of the indexed attribute by which values are filtered |
| sortOrder       | Sorting order DESC, ASC                                               |
| sortKey         | Sorting field, possible values: id, title, date, position             |
| limit           | Parameter for pagination, default 30                                  |
| offset          | Parameter for pagination, default 0                                   |

```kotlin
val result = OneEntryProducts.instance.products(langCode = "en_US")
```

The **ProductsResult** will come as an response

```kotlin
/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)
```

#### Getting all products uncategorized, with pagination

| Parameter | Description                                               |
|-----------|-----------------------------------------------------------|
| langCode  | Locale code, used only when searching with a filter       |
| sortOrder | Sorting order DESC, ASC                                   |
| sortKey   | Sorting field, possible values: id, title, date, position |
| limit     | Parameter for pagination, default 30                      |
| offset    | Parameter for pagination, default 0                       |

```kotlin
val result = OneEntryProducts.instance.emptyPageProducts(langCode = "en_US")
```

The **ProductsResult** will come as an response

```kotlin
/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)
```

#### Getting all products in the category by its id, with pagination

| Parameter | Description                                               |
|-----------|-----------------------------------------------------------|
| page id   | Category id                                               |
| langCode  | Locale code, used only when searching with a filter       |
| sortOrder | Sorting order DESC, ASC                                   |
| sortKey   | Sorting field, possible values: id, title, date, position |
| limit     | Parameter for pagination, default 30                      |
| offset    | Parameter for pagination, default 0                       |

```kotlin
val result = OneEntryProducts.instance.products(pageId = 12, langCode = "en_US")
```

The **ProductsResult** will come as an response

```kotlin
/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)
```

#### Getting all products in the category by its url, with pagination

| Parameter | Description                                               |
|-----------|-----------------------------------------------------------|
| page url  | Category url                                              |
| langCode  | Locale code, used only when searching with a filter       |
| sortOrder | Sorting order DESC, ASC                                   |
| sortKey   | Sorting field, possible values: id, title, date, position |
| limit     | Parameter for pagination, default 30                      |
| offset    | Parameter for pagination, default 0                       |

```kotlin
val result = OneEntryProducts.instance.products(url = "heroes", langCode = "en_US")
```

The **ProductsResult** will come as an response

```kotlin
/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)
```

#### Getting related products for another product by its id

| Parameter  | Description                                                      |
|------------|------------------------------------------------------------------|
| product id | Id of the product for which you want to get the related products |
| langCode   | Locale code, used only when searching with a filter              |
| sortOrder  | Sorting order DESC, ASC                                          |
| sortKey    | Sorting field, possible values: id, title, date, position        |
| limit      | Parameter for pagination, default 30                             |
| offset     | Parameter for pagination, default 0                              |

```kotlin
val result = OneEntryProducts.instance.relatedProducts(id = 8, langCode = "en_US")
```

The **ProductsResult** will come as an response

```kotlin
/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)
```

#### Getting a product by its id

| Parameter | Description                                         |
|-----------|-----------------------------------------------------|
| id        | product ID                                          |
| langCode  | Locale code, used only when searching with a filter |

```kotlin
val result = OneEntryProducts.instance.products(id = 8, langCode = "en_US")
```

The answer will return **ProductModel**

```kotlin
/**
 * OneEntry catalog products
 *
 * @param id Product Id
 * @param localizeInfos Product localize info
 * @param price Product price
 * @param attributeValues Product attributes
 */
@Serializable
data class ProductModel(
    val id: Int,
    val localizeInfos: Map<String, LocalizeInfo>,
    val price: Double?,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Product filter

| Parameter | Description                                               |
|-----------|-----------------------------------------------------------|
| langCode  | Locale code, used only when searching with a filter       |
| sortOrder | Sorting order DESC, ASC                                   |
| sortKey   | Sorting field, possible values: id, title, date, position |
| limit     | Parameter for pagination, default 30                      |
| offset    | Parameter for pagination, default 0                       |

```kotlin
val filter = listOf(
            ProductsFilter(
                attributeMarker = "price",
                conditionMarker = "lth",
                conditionValue = 260,
                pageId = 1
            )
        )

val result = OneEntryProducts.instance.filterProducts(filter, langCode = "en_US")
```

In all cases of filtering, the answer will be **ProductResult**

```kotlin
/**
 * The structure that comes when you request products
 *
 * @param items All products
 * @param total Number of products
 */
@Serializable
data class ProductsResult(
    val items: List<ProductModel>,
    val total: Int
)
```

#### Quick search

| Parameter | Description                                                                                                                      |
|-----------|----------------------------------------------------------------------------------------------------------------------------------|
| name      | Text to search product page objects (search is based on the title field of the localizeInfos object with language consideration) |
| langCode  | Locale code, used only when searching with a filter                                                                              |

```kotlin
val result = OneEntryProducts.instance.quickSearch(name = "cat", langCode = "en_US")
```

The answer will be the **SearchProduct** array

```kotlin
/**
 * OneEntry search products
 *
 * @param id Product Id
 * @param title Product title
 * @param pageId Id of the page to which the product is linked
 */
@Serializable
data class SearchProduct(
    val id: Int,
    val title: String,
    val pageId: Int
)

```

#### Product statuses

##### Model

```kotlin
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
```

##### All statuses

```kotlin
val statuses: List<ProductStatus> = OneEntryProducts.instance.productStatuses()
```

##### Status by id

```kotlin
val status: ProductStatus = OneEntryProducts.instance.productStatus(id = 2)
```

##### Status by marker

```kotlin
val status: ProductStatus = OneEntryProducts.instance.productStatus(marker = "storage")
```

##### Status marker validation

You can check the validation of the token either using the appropriate method

```kotlin
val valid: Boolean = OneEntryProducts.instance.productStatusMarkerValidation(marker = "storage")
```

