# ext4klaxon
Type Extensions for [Klaxon](https://github.com/cbeust/klaxon) (Kotlin JSON library)

## Current extensions:
* Long
* Int
* Enum

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

## Download

**ext4klaxon is under development.**

