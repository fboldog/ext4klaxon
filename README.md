# ext4klaxon
Type Extensions for [Klaxon](https://github.com/cbeust/klaxon) (Kotlin JSON library)

## Current extensions:
* Long
* Int
* Enum
* Date

### Long
Get Long, Double, Float, String as Int, without loosing precision when it is convertable
```kotlin
val i = jsonObject.intStrict("fieldName")
```

### Int
Get Int, Double, Float or String as Long, when it is convertable
```kotlin
val l = jsonObject.intStrict("fieldName")
```

### Enum
Convert any string to enum:
```kotlin
val e = jsonObject.enum<EnumType>("fieldName")
```

```kotlin
val e = jsonObject.enumFromValues("fieldName", Enum.values())
```

```kotlin
val e = jsonObject.enumFromFunction("fieldName", {f->function(f)})
```

### Date
Get any whole Numeric as Date
```kotlin
val e = jsonObject.date("fieldName")
```


## Download

**ext4klaxon is under development.**

Gradle:
```groovy
compile 'com.fboldog.ext4klaxon:ext4klaxon:0.3.1'
```

or Maven:
```xml
<dependency>
  <groupId>com.fboldog.ext4klaxon</groupId>
  <artifactId>ext4klaxon</artifactId>
  <version>0.3.1</version>
</dependency>
```
