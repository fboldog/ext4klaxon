# ext4klaxon
Type Extensions for [Klaxon](https://github.com/cbeust/klaxon) (Kotlin JSON library)

[![Build Status](https://travis-ci.org/fboldog/ext4klaxon.svg)](https://travis-ci.org/fboldog/ext4klaxon)

There is three extension for:
- Long
- Int
- Enum

Get Long, Double, Float, String as Int, without loosing precision when it is convertable
```kotlin
val i = jsonObject.intStrict("fieldName")
```

Get Int, Double, Float or String as Long, when it is convertable
```kotlin
val l = jsonObject.intStrict("fieldName")
```

Convert any string to enum:
```kotlin
val e = jsonObject.enum<EnumType>("fieldName")
```

```kotlin
val e = jsonObject.enumFromValues("fieldName", Enum.values())
```
