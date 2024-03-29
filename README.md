# OneEntry Kotlin SDK



- [Add OneEntry SDK to your Kotlin project](#add-oneentry-sdk-to-your-kotlin-project)
  - [Step 1: Create OneEntry project](#step-1-create-oneentry-project)
  - [Step 2: Register your app with OneEntry (In Development)](#step-2-register-your-app-with-oneentry)
  - [Step 3: Add OneEntry SDK to your app](#step-3-add-oneentry-sdk-to-your-app)
- [Using OneEntry Kotlin SDK](#using-oneentry-kotlin-sdk)
  - [Working with blocks](#working-with-blocks)
    - [Getting a block by its marker](#getting-a-block-by-its-marker)
    - [Getting all block objects with pagination](#getting-all-block-objects-with-pagination)
    - [Get similar products attached to the block](#get-similar-products-attached-to-the-block)
    - [Get products from categories attached to the block](#get-products-from-categories-attached-to-the-block)
  - [Working with forms](#working-with-forms)
    - [Getting all forms](#getting-all-forms)
    - [Getting a form by its marker](#getting-a-form-by-its-marker)
    - [Sending data to the form](#sending-data-to-the-form)
    - [Getting all form data](#getting-all-form-data)
    - [Getting form data from its marker](#getting-form-data-from-its-marker)
  - [Working with attributes](#working-with-attributes)
    - [Introduction](#introduction)
    - [Available data types](#available-data-types)
    - [Receiving attributes](#receiving-attributes)
      - [Custom processing](#custom-processing)
      - [Processing of numerical values](#processing-of-numerical-values)
      - [Date and time processing](#date-and-time-processing)
      - [Files processing](#files-processing)
      - [Images processing](#images-processing)
      - [Text data types](#text-data-types)
        - [String](#string)
        - [Text](#text)
        - [Text with header](#text-with-header)
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
    - [Getting the root pages](#getting-the-root-pages)
    - [Getting all pages within the catalog](#getting-all-pages-within-the-catalog)
    - [Getting all the pages](#getting-all-the-pages)
    - [Getting child pages with product information as an array](#getting-child-pages-with-product-information-as-an-array)
    - [Getting pages for the related form by URL](#getting-pages-for-the-related-form-by-url)
    - [Getting pages for the related block by URL](#getting-pages-for-the-related-block-by-url)
    - [Getting one page with all the information](#getting-one-page-with-all-the-information)
      - [Receiving by id](#receiving-by-id)
      - [Receiving by URL](#receiving-by-url)
    - [Getting a page config](#getting-a-page-config)
      - [Receiving by URL](#receiving-by-url)
    - [Quick search page](#quick-search-page)
  - [OneEntryProject](#oneentryproject)
    - [Getting all administrators](#getting-all-administrators)
    - [Getting all active locales](#getting-all-active-locales)
    - [Getting all general types](#getting-all-general-types)
    - [Getting a menu item by its marker](#getting-a-menu-item-by-its-marker)
  - [OneEntryFiles](#oneentryfiles)
    - [File uploading](#file-uploading)
    - [Deleting files](#deleting-files)
  - [OneEntrySystem](#oneentrysystem)
    - [Testing error 404](#testing-error-404)
    - [Testing error 500](#testing-error-500)
  - [OneEntryTemplates](#oneentrytemplates)
    - [Getting all templates](#getting-all-templates)
      - [Available types by properties](#available-types-by-properties)
    - [Request templates by type](#request-templates-by-type)
  - [OneEntryTemplatePreviews](#oneentrytemplatepreviews)
    - [Getting all preview templates](#getting-all-preview-templates)
    - [Getting a preview template by its id](#getting-a-preview-template-by-its-id)
    - [Getting a preview template by its marker](#getting-a-preview-template-by-its-marker)


### Step 1: Create OneEntry project

In order to create a OneEntry project, you need to create an account on our [site](https://account.oneentry.cloud/).
After that, go to the projects tab and create a new [project](https://account.oneentry.cloud/projects).

### Step 2: Add OneEntry SDK to your app

Go to `settings.gradle.kts` and add the following configuration:

```kotlin
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven {
      url = uri("https://maven.pkg.github.com/OneEntry/oneentry-kotlin-sdk")
    }
  }
}
```

Now go to `build.gradle.kts` and add implementation:

```kotlin
implementation("com.oneentry:oneentry-kotlin:1.3.0")
```

### Step 3: Initialize OneEntry in your app

This is the final step you need to take in order to add OneEntry to your application.
> Before using SDK classes and methods, you need to go through initialization. Otherwise there will be an error

#### Initialization with an authentication token

1. To initialize with an authorization token, you need to get a link to the project and the token. You can do this in the project settings in the tab `App tokens`
2. Import the OneEntry module:
```kotlin
import com.example.oneentry.network.OneEntryProducts

class Example() {

  val provider = OneEntryProducts.instance

  /..
}
``` 
3. Configure a OneEntryCore shared instance, before using SDK methods
```kotlin
val token = "..."
val domain = "https://sample.oneentry.cloud"
val credential = OneEntryTokenCredential(token)

OneEntryCore.initializeApp(domain, credential)
```

#### Initialization with certificates

> Android Sdk OneEntry uses .p12(PKCS#12) certificates for authorization. Such a certificate can be generated independently from the key & certificate pair or use the one provided by OneEntry

In any case, you need to download the archive with certificates. You can do this in the project settings, in the Application Certificates tab
The following files will be inside:
* `.cert` - Certificate, encrypted in base64
* `.csr` - Certificate request
* `.key` - Key, encrypted in base64
* `.p12` - The Certificate & Key bundle, encrypted in PKCS#12

##### Using the built-in certificate

1. Move the .p12 file to the project, make sure it is included in assets
2. In the onCreate method of your MainActivity do the following:

   > 1. Create an instance of the application context.
   > 2. Specify the name of the `.p12` file (example: certificate.p12).
   > 3. Get the AssetManager using `context.assets`. AssetManager allows you to access assets in the assets folder.
   > 4. Open an inputStream to read the `.p12` file from the assets folder.
   > 5. Create an outputStream to write the `.p12` file to your application's `filesDir` directory.
   > 6. Copy the contents of the input stream to the output stream using `inputStream.copyTo(outputStream)`.
   > 7. Close the input and output streams using `inputStream.close()` and `outputStream.close()` respectively.
   >
   > This way, all the content of the .p12 file from the assets folder is copied to the filesDir directory of your application.
```kotlin
val context = App.applicationContext()
val fileName = "certificate.p12"
val assetManager = context.assets
val inputStream = assetManager.open(fileName)
val outputStream = FileOutputStream(File(context.filesDir, fileName))
inputStream.copyTo(outputStream)

inputStream.close()
outputStream.close()
```
2. Initialize the application:
```kotlin
val domain = "sdk-sample.oneentry.cloud"
val fileName = "certificate.p12"
val filePath = File(context.filesDir, fileName).absolutePath
val password = "sample"

val credential = OneEntryCertificateCredential(filePath, password)

OneEntryCore.initializeApp(domain, credential)
```

##### Generation of your .p12 certificate

1. To do this, you will need a key(`.key`) and certificate(`.cert`) file, download them
2. Generate .p12 with `openSSL`:

   ``openssl pkcs12 -export -out certificate.p12 -inkey key.key -in cert.cert  ``
3. Create export password
4. Move the .p12 file to the project in assets directory. The assets folder is located inside the `app/src/main` project directory. If this folder does not exist, you can create it manually.

Here's how to create the assets folder:

    1. Right-click on the main folder in the app/src/main directory.
    2. From the context menu, select "New" and then "Directory".
    3. Enter a name for the assets folder and click Finish.
5. Using the certificate and password you specified when creating it, initialize the application
## Using OneEntry Kotlin SDK

### Working with blocks

Controllers for working with block objects

#### Getting a block by its marker

This method automatically detects the type and loads the data of the product blocks.
If you need manual control to get products, use `OneEntryBlocks.instance.products()` or `OneEntryBlocks.instance.similarProducts()`

```kotlin
val block = OneEntryBlocks.instance.block(marker = "dev", langCode = "en_US")
```

`OneEntryBlock` will return as a result
```kotlin
/**
 * Block information about the object
 *
 * @param id Block id
 * @param attributeSetId Identifier for the used attribute set
 * @param localizeInfos Block localize info
 * @param version Object version number
 * @param identifier Block status marker
 * @param attributeValues Block attributes
 */
@Serializable
data class OneEntryBlock(
    val id: Int,
    val attributeSetId: Int?,
    val localizeInfos: Map<String, LocalizeInfo>,
    val version: Int,
    val identifier: String,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting all block objects with pagination

Get all block objects. The result of this method does not include the content of product blocks.

```kotlin
val blocks = OneEntryBlocks.instance.blocks(langCode = "en_US")
```

List `OneEntryBlock` will return as a result 
```kotlin
/**
 * Block information about the object
 *
 * @param id Block id
 * @param attributeSetId Identifier for the used attribute set
 * @param localizeInfos Block localize info
 * @param version Object version number
 * @param identifier Block status marker
 * @param attributeValues Block attributes
 */
@Serializable
data class OneEntryBlock(
    val id: Int,
    val attributeSetId: Int?,
    val localizeInfos: Map<String, LocalizeInfo>,
    val version: Int,
    val identifier: String,
    val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Get similar products attached to the block

Get similar products (based on conditions in block) attached to the block.

```kotlin
val similarProducts = OneEntryBlocks.instance.similarProducts(marker = "dev", langCode = "en_US")
```

The `ProductsResult` will come as an response
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

#### Get products from categories attached to the block

Get products from categories attached to the block.

```kotlin
val products = OneEntryBlocks.instance.products(marker = "dev", langCode = "en_US")
```

The `ProductsResult` will come as an response
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

### Working with forms

OneEntry forms allow you to send all kinds of data to the admin application

#### Getting all forms

```kotlin
val forms: List<OneEntryForm> = OneEntryForms.instance.forms(langCode = "en_US")
```

`OneEntryForm` array will return as a result`

```kotlin
/**
 * OneEntry form model
 *
 * @param id Form id
 * @param localizeInfos Form localize info
 * @param attributeValues Form attributes
 * @param version Form status version number
 * @param identifier Form status marker
 */
@Serializable
data class OneEntryForm(
  val id: Int,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>? = null,
  val version: Int,
  val identifier: String,
  val processingType: String?
)
```

#### Getting a form by its marker

```kotlin
val form: OneEntryForm = OneEntryForms.instance.form(marker = "auth", langCode = "en_US")
```

`OneEntryForm` array will return as a result`

```kotlin
/**
 * OneEntry form model
 *
 * @param id Form id
 * @param localizeInfos Form localize info
 * @param attributeValues Form attributes
 * @param version Form status version number
 * @param identifier Form status marker
 */
@Serializable
data class OneEntryForm(
  val id: Int,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>? = null,
  val version: Int,
  val identifier: String,
  val processingType: String?
)
```

#### Sending data to the form

```kotlin
val data: Map<String, List<OneEntryFormData>> = mapOf(
  "en_US" to listOf(
    OneEntryFormData("login", "Dino"),
    OneEntryFormData("password", "544")
  )
)

val response = OneEntryForms.instance.sendData(identifier = "auth", data = data)
```

`OneEntryFormDataResponse` will return as a response

```kotlin
@Serializable
data class OneEntryFormDataResponse(
  val items: List<OneEntryFormDataResponseBody>,
  val total: Int
)

/**
 * Represents a response containing form data for a single entry with an identity and time
 *
 * @param id Unique identifier for the form data response
 * @param time Time when the form data was submitted
 * @param formIdentifier Identifier for the form
 * @param formData Form data organized as a dictionary of arrays of OneEntryFormData
 */
@Serializable
data class OneEntryFormDataResponseBody(
  val id: Int,
  val time: String,
  val formIdentifier: String,
  val formData: Map<String, List<OneEntryFormData>>
)
```

#### Getting all form data

```kotlin
val data: OneEntryFormDataResponse = OneEntryForms.instance.data()
```

`OneEntryFormDataResponse` array will return as a response

```kotlin
@Serializable
data class OneEntryFormDataResponse(
  val items: List<OneEntryFormDataResponseBody>,
  val total: Int
)

/**
 * Represents a response containing form data for a single entry with an identity and time
 *
 * @param id Unique identifier for the form data response
 * @param time Time when the form data was submitted
 * @param formIdentifier Identifier for the form
 * @param formData Form data organized as a dictionary of arrays of OneEntryFormData
 */
@Serializable
data class OneEntryFormDataResponseBody(
  val id: Int,
  val time: String,
  val formIdentifier: String,
  val formData: Map<String, List<OneEntryFormData>>
)
```

#### Getting form data from its marker

```kotlin
val data: OneEntryFormDataResponse = OneEntryForms.instance.data(marker = "marker")
```

`OneEntryFormDataResponse` array will return as a response

```kotlin
@Serializable
data class OneEntryFormDataResponse(
  val items: List<OneEntryFormDataResponseBody>,
  val total: Int
)

/**
 * Represents a response containing form data for a single entry with an identity and time
 *
 * @param id Unique identifier for the form data response
 * @param time Time when the form data was submitted
 * @param formIdentifier Identifier for the form
 * @param formData Form data organized as a dictionary of arrays of OneEntryFormData
 */
@Serializable
data class OneEntryFormDataResponseBody(
  val id: Int,
  val time: String,
  val formIdentifier: String,
  val formData: Map<String, List<OneEntryFormData>>
)
```

### Working with attributes

#### Introduction

The **OneEntryAttribute** data structure is provided to work with attributes in OneEntry

```kotlin
/**
 * OneEntry entity attribute
 *
 * @param type Type of attribute
 * @param value Value of attribute
 */
@Serializable
data class AttributeModel(
  var type: AttributeType,
  var value: JsonElement
) {

  val asInt: Int?
    get() = instance.serializer.decodeFromJsonElementOrNull(value)

  val asString: String?
    get() = instance.serializer.decodeFromJsonElementOrNull(value)

  val asText: List<OneEntryText>?
    get() = instance.serializer.decodeFromJsonElementOrNull(value)

  val asImage: List<OneEntryImage>?
    get() = instance.serializer.decodeFromJsonElementOrNull(value)

  val asDateTime: OneEntryDateTime?
    get() = instance.serializer.decodeFromJsonElementOrNull(value)

  val asTextWithHeader: List<OneEntryTextWithHeader>?
    get() = instance.serializer.decodeFromJsonElementOrNull(value)
}
```

`value` - Attribute value
`type` - Attribute data type

#### Available data types

```kotlin
/**
 * Type of attribute
 *
 * @param integer Integer attribute
 * @param date Date attribute without time
 * @param file File attribute
 * @param list List attribute
 * @param real Attribute with floating point
 * @param spam Spam attribute
 * @param text Text attribute
 * @param time Time attribute without date
 * @param float Attribute with floating point
 * @param button Button attribute
 * @param image Image attribute
 * @param string String attribute
 * @param dateTime Date & time attribute
 * @param textWithHeader Text attribute with header
 * @param groupOfImages Group of image attribute
 * @param radioButton Radio button attribute
 */
@Serializable
enum class AttributeType {

  @SerialName("integer")
  INTEGER,
  @SerialName("date")
  DATE,
  @SerialName("file")
  FILE,
  @SerialName("list")
  LIST,
  @SerialName("real")
  REAL,
  @SerialName("spam")
  SPAM,
  @SerialName("text")
  TEXT,
  @SerialName("time")
  TIME,
  @SerialName("float")
  FLOAT,
  @SerialName("button")
  BUTTON,
  @SerialName("image")
  IMAGE,
  @SerialName("string")
  STRING,
  @SerialName("dateTime")
  DATE_TIME,
  @SerialName("textWithHeader")
  TEXT_WITH_HEADER,
  @SerialName("groupOfImages")
  GROUP_OF_IMAGES,
  @SerialName("radioButton")
  RADIO_BUTTON
}
```

#### Receiving attributes

Let's try to get the attributes from the page, for products and other entities the process will be similar

```kotlin
val page = OneEntryPages.instance.page("dev", "en_US")
val attribute = page.attributeValues?.get("en_US")?.get("int")
```

#### Custom processing

If you are sure that your attribute has accepted a specific data type, you can process `value` yourself

```kotlin
val value = attribute?.value as? Int
```

#### Processing of numerical values

All numeric OneEntry values (`real`, `integer`, `float`) can be easily converted to Swift data types

```kotlin
val attribute = page.attributeValues?.get("en_US")?.get("int")
val intAttribute  = attribute?.asInt
val doubleAttribute  = attribute?.asDouble
val stringAttribute  = attribute?.asString

println(intAttribute) // 500
println(doubleAttribute) // 500.0
println(stringAttribute) // "500"
```

#### Date and time processing

OneEntry has several types of date and time data:

* `Date`
* `Time`
* `DateTime`

All of them will eventually be converted to a Kotlin `Date` object

```kotlin
val date = page.attributeValues?.get("en_US")?.get("date").asDate
val time = page.attributeValues?.get("en_US")?.get("time").asDate
val dateTime = page.attributeValues?.get("en_US")?.get("date_time").asDateTime
```

#### Files processing

```kotlin
val attribute = page.attributeValues?.get("en_US")?.get("file").asFile
```

The `OneEntryFile` array will be returned as a response

```kotlin
/**
 * Represents a file associated with a single entry
 *
 * @param filename The full path to the file
 * @param downloadLink The download link or URL for accessing the file
 * @param size The size of the file in bytes
 */
@Serializable
data class OneEntryFile(
  val filename: String,
  val downloadLink: String,
  val size: Int
)
```

#### Images processing

Attribute types such as `image` and `groupOfImages` are treated by sdk in the same way

```kotlin
val attributes = page.attributeValues?.get("en_US")?.get("file").asImage
```

The `OneEntryImage` array will be returned as an answer

```kotlin
/**
 * OneEntry image model
 *
 * @param size Image size
 * @param filename Full path to image
 * @param previewLink Preview link
 * @param downloadLink Link to download the image
 */
@Serializable
data class OneEntryImage(
  val size: Int,
  val filename: String,
  val previewLink: String,
  val downloadLink: String
)
```

#### Text data types

##### String

Most types of attributes can be obtained as a string

```kotlin
val value = page.attributeValues?.get("en_US")?.get("string").asString
```

##### Text

```kotlin
val attribute = page.attributeValues?.get("en_US")?.get("text").asText
```

The `OneEntryText` structure will be returned as an answer

```kotlin
/**
 * Object of text
 *
 * @param htmlValue String value
 * @param plainValue The plain text value associated with the object
 */
@Serializable
data class OneEntryText(
  @Serializable(with = HTMLParsingSerializer::class)
  val htmlValue: String,
  val plainValue: String
)
```

##### Text with header

```kotlin
val attribute = page.attributeValues?.get("en_US")?.get("header_text").asTextWithHeader
```

The array of `OneEntryTextWithHeader` structures will return as an answer

```kotlin
/**
 * @param id The unique identifier for the object
 * @param index The unique identifier for the object
 * @param header The header information for the object
 * @param htmlValue The HTML value associated with the object
 * @param plainValue The plain text value associated with the object
 */
@Serializable
data class OneEntryTextWithHeader(
  val id: String,
  val index: String,
  val header: String,
  val htmlValue: String,
  val plainValue: String
)

```

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


### OneEntryPages

#### Getting the root pages

Getting top-level pages. These are the pages whose **parentId** is equal to **null**

```kotlin
val root: List<OneEntryPage> = OneEntryPages.instance.rootPages(langCode = "en_US")
```

The **OneEntryPage** array will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting all pages within the catalog

All pages that are contained in the 'catalog' tab. In other words, the categories of products.

| Parameter | Description                       |
|-----------|-----------------------------------|
| langCode  | Language code. For example: en_US |
| limit     | Limit of pagination elements      |
| offset    | Pagination offset                 |

```kotlin
val catalog: List<OneEntryPage> = OneEntryPages.instance.catalogPages(langCode = "en_US")
```

The **OneEntryPage** array will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting all the pages

Getting all OneEntry pages

```kotlin
val pages: List<OneEntryPage> = OneEntryPages.instance.catalogPages(langCode = "en_US")
```

The **OneEntryPage** array will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting child pages with product information as an array

```kotlin
val pages: List<OneEntryPage> = OneEntryPages.instance.pagesChildren(url = "heroes")
```

The **OneEntryPage** will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting pages for the related form by URL

```kotlin
val pages: List<OneEntryPage> = OneEntryPages.instance.pagesForms(url = "heroes")
```

The **OneEntryPage** will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting pages for the related block by URL

```kotlin
val pages: List<OneEntryPage> = OneEntryPages.instance.pagesBlocks(url = "heroes")
```

The **OneEntryPage** will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting one page with all the information

##### Receiving by id

```kotlin
val page: OneEntryPage = OneEntryPages.instance.page(id = 12, langcode = "en_US")
```

The **OneEntryPage** will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

##### Receiving by URL

```kotlin
val page: OneEntryPage = OneEntryPages.instance.page(url = "heroes", langcode = "en_US")
```

The **OneEntryPage** will be returned as an answer

```kotlin
/**
 * OneEntry page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param depth Page depth
 * @param isVisible Is the page active
 * @param position Page position
 * @param type Page type
 * @param templateIdentifier Page template marker
 * @param localizeInfos Page localize content
 * @param attributeValues Page attributes
 */
@Serializable
data class OneEntryPage(
  val id: Int,
  val parentId: Int?,
  val pageUrl: String,
  val depth: Int?,
  val isVisible: Boolean?,
  val position: Int? = null,
  val type: String?,
  val templateIdentifier: String?,
  val localizeInfos: Map<String, LocalizeInfo>?,
  val attributeValues: Map<String, Map<String, AttributeModel>>?
)
```

#### Getting a page config

##### Receiving by URL

```kotlin
val config: OneEntryPageConfig = OneEntryPages.instance.config(url = "heroes")
```

The **OneEntryPageConfig** will be returned as an answer

```kotlin
/**
 * Page config
 *
 * @param rowsPerPage Rows value per page
 * @param productsPerRow Products value per row
 */
@Serializable
data class OneEntryPageConfig(
  val rowsPerPage: Int?,
  val productsPerRow: Int?
)
```

#### Quick search page

This method searches for pages by keywords

```kotlin
val result: List<OneEntrySearchPage> = OneEntryPages.instance.quickSearch(name = "heroes", langCode = "en_US")
```

The **OneEntrySearchPage** array will be returned as an answer

```kotlin
/**
 * OneEntry page search dto
 *
 * @param id Page id
 * @param title Page title
 */
@Serializable
data class OneEntrySearchPage(
  val id: Int,
  val title: String
)
```


### OneEntryProject

#### Getting all administrators

```kotlin
val admins = OneEntryProject.instance.admins(langCode = "en_US")
```

The answer will be array of `OneEntryAdmin`

```kotlin
/**
 * Admin model
 *
 * @param id Admin id
 * @param identifier Admin marker
 * @param position Admin position
 */
@Serializable
data class OneEntryAdmin(
  val id: Int,
  val identifier: String,
  val position: Int?
)
```

#### Getting all active locales

```kotlin
val locales = OneEntryProject.instance.locales()
```

The answer will be array of `OneEntryLocale`

```kotlin
/**
 * Localization model
 *
 * @param id Locale id
 * @param shortCode Locale short code
 * @param code Locale full code
 * @param name Locale name
 * @param nativeName Locale native name
 * @param isActive Is locale active
 * @param image Locale icon
 * @param position Locale position
 */
@Serializable
data class OneEntryLocale(
  val id: Int,
  val shortCode: String,
  val code: String,
  val name: String,
  val nativeName: String,
  val isActive: Boolean,
  val image: String?,
  val position: Int?
)
```

#### Getting all general types

```kotlin
val types = OneEntryProject.instance.generalTypes()
```

The answer will be array of `OneEntryGeneralType`

```kotlin
/**
 * OneEntry general type
 *
 * @param id Generale type id
 * @param type General type string value
 */
@Serializable
data class OneEntryGeneralType(
  val id: Int,
  val type: String
)
```

#### Getting a menu item by its marker

The menu is a very important essence of OneEntry. It allows you to group pages by features. These pages will be returned as a tree, and you can easily get all the subpages (children) of each page

```kotlin
val menu = OneEntryProject.instance.menu("dev")
```

The answer will be the `OneEntryMenu` structure

```kotlin
/**
 * Structure of the OneEntry menu item
 *
 * @param id Menu id
 * @param identifier Menu marker
 * @param localizeInfos Menu localize info
 * @param pages Pages inside the menu item
 */
@Serializable
data class OneEntryMenu(
  val id: Int,
  val identifier: String,
  val localizeInfos: Map<String, LocalizeInfo>,
  val pages: List<OneEntryMenuPage>
)
```

```kotlin
/**
 * OneEntry menu page object
 *
 * @param id Page id
 * @param parentId Page parent id
 * @param pageUrl Page url
 * @param position Page position
 * @param localizeInfos Page localize content
 * @param children Child pages
 */
@Serializable
data class OneEntryMenuPage(
  val id: Int,
  val parentId: Int? = null,
  val pageUrl: String,
  val position: Int,
  val localizeInfos: Map<String, LocalizeInfo>? = null,
  val children: List<OneEntryMenuPage>? = null
)
```

### OneEntryFiles

#### File uploading

OneEntry supports the ability to save your files to storage. To do this, you need to specify additional information about the file.

| Field    | Description                                                                                         |
|----------|-----------------------------------------------------------------------------------------------------|
| fileURL  | Path to the file you want to save                                                                   |
| type     | Type, determines the folder name in the storage                                                     |
| entity   | Entity name from which the file is uploaded, determines the folder name in the storage              |
| id       | Identifier of the object from which the file is uploaded, determines the folder name in the storage |
| width    | Width parameter                                                                                     |
| height   | Height parameter                                                                                    |
| compress | Boolean flag of optimization (compression) for images                                               |

```kotlin
val fileUrl = ".../dev.png"
val type = "page"
val entity = "editor"
val id = 3787

val result = provider.uploadFile(fileUrl, type, entity, id)
```

The `OneEntryFile` array will be returned as a response

```kotlin
/**
 * Represents a file associated with a single entry
 *
 * @param filename The full path to the file
 * @param downloadLink The download link or URL for accessing the file
 * @param size The size of the file in bytes
 */
@Serializable
data class OneEntryFile(
  val filename: String,
  val downloadLink: String,
  val size: Int
)
```

#### Deleting files

This SDK method allows you to delete saved files. Additional fields must also be specified for it.

| Field  | Description                                                                                         |
|--------|-----------------------------------------------------------------------------------------------------|
| name   | File name                                                                                           |
| type   | Type, determines the folder name in the storage                                                     |
| entity | Entity name from which the file is uploaded, determines the folder name in the storage              |
| id     | Identifier of the object from which the file is uploaded, determines the folder name in the storage |

```kotlin
val filename = "dev.png"
val type = "page"
val entity = "editor"
val id = 3787

val result = provider.deleteFile(filename, type, entity, id)
```

### OneEntrySystem

#### Testing error 404

```kotlin
try {
  OneEntrySystem.instance.test404()
} catch (error: OneEntryException) {
  assertEquals(404, error.statusCode)
}
```

#### Testing error 500

```kotlin
try {
  OneEntrySystem.instance.test500()
} catch (error: OneEntryException) {
  assertEquals(500, error.statusCode)
}
```

### OneEntryTemplates

#### Getting all templates

```kotlin
val templates = OneEntryTemplates.instance.templates()
```

This property returns an array of `OneEntryAllTemplates`. This model contains the `templateDictionary` field, which stores the data of all templates by key.

```kotlin
val templates = OneEntryTemplates.instance.templates()
templates.templateDictionary["forCatalogProducts"] // forCatalogProducts templates as array of OneEntryTemplate
```

There are also have properties to quickly get basic templates

```kotlin
val templates = OneEntryTemplates.instance.templates()
templates.forCatalogProducts // forCatalogProducts templates as array of OneEntryTemplate
```

##### Available types by properties

* forCatalogProducts
* forBasketPage
* forErrorPage
* forCatalogPages
* forProductPreview
* forProductPage
* forSimilarProductBlock
* forStatisticProductBlock
* forProductBlock
* forForm
* forFormField
* forNewsPage
* forNewsBlock
* forNewsPreview
* forOneNewsPage
* forUsualPage
* forTextBlock
* forSlider
* service

#### Request templates by type

In order not to request all the templates from the server and search for the right one among them, you can immediately send the data according to the desired type.

```kotlin
val templates = OneEntryTemplates.instance.templates("forCatalogProducts")
```

### OneEntryTemplatePreviews

#### Getting all preview templates

```kotlin
val previews: List<OneEntryTemplatePreview> = OneEntryTemplatePreviews.instance.templates()
```

The **OneEntryTemplatePreview** array will be responsed

```kotlin
/**
 * Represents a template preview for a single entry, with support for localization
 *
 * @param id Unique identifier for the preview
 * @param version Version of the template, if available
 * @param identifier Identifier for the template
 * @param localizeInfos Localization information organized as a dictionary of LocalizeInfo objects, if available
 */
@Serializable
data class OneEntryTemplatePreview(
  val id: Int,
  val version: Int,
  val identifier: String,
  val localizeInfos: Map<String, LocalizeInfo>?
)
```

#### Getting a preview template by its id

```kotlin
val preview: OneEntryTemplatePreview = OneEntryTemplatePreviews.instance.template(id = 1)
```

The **OneEntryTemplatePreview** model will be responsed

```kotlin
/**
 * Represents a template preview for a single entry, with support for localization
 *
 * @param id Unique identifier for the preview
 * @param version Version of the template, if available
 * @param identifier Identifier for the template
 * @param localizeInfos Localization information organized as a dictionary of LocalizeInfo objects, if available
 */
@Serializable
data class OneEntryTemplatePreview(
  val id: Int,
  val version: Int,
  val identifier: String,
  val localizeInfos: Map<String, LocalizeInfo>?
)
```

#### Getting a preview template by its marker

```kotlin
val preview: OneEntryTemplatePreview = OneEntryTemplatePreviews.instance.template(marker = "preview")
```

The **OneEntryTemplatePreview** model will be responsed

```kotlin
/**
 * Represents a template preview for a single entry, with support for localization
 *
 * @param id Unique identifier for the preview
 * @param version Version of the template, if available
 * @param identifier Identifier for the template
 * @param localizeInfos Localization information organized as a dictionary of LocalizeInfo objects, if available
 */
@Serializable
data class OneEntryTemplatePreview(
  val id: Int,
  val version: Int,
  val identifier: String,
  val localizeInfos: Map<String, LocalizeInfo>?
)
```
