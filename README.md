# Immutable Collections

[![Maven Central](https://img.shields.io/maven-central/v/net.codinux.collections/immutable-collections.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.codinux.collections%22%20AND%20a:%22immutable-collections%22)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)


Immutable Collection implementations for Kotlin Multiplatform for the targets JVM, Android, iOS, macOS, watchOS, tvOS, 
Linux, Windows, Node.js, JS Browser and WebAssembly.

Often it's not desirable to pass mutable state to the outside. Even though Kotlin's `List<E>` interface seems to be immutable, 
a simple `as MutableList<E>` makes it mutable so that class internal state can be changed from the outside. 

To avoid this all this library's collection implementations - ImmutableList, ImmutableSet, ImmutableMap, ImmutableCollection - 
are immutable. Also changes to underlying collection don't change their state.

## Setup

### Gradle:

```
dependencies {
  implementation "net.codinux.collections:immutable-collections:1.0.0"
}
```

### Maven:

Maven does not support automatic platform resolution as Gradle does, therefore the specific platform must be specified here:

```
<dependency>
   <groupId>net.codinux.collections</groupId>
   <artifactId>immutable-collections-jvm</artifactId>
   <version>1.0.0</version>
</dependency>
```


## Usage

### ImmutableList

```kotlin
val listViaConstructor = ImmutableList("foo", "bar")
val listViaGlobalMethod = immutableListOf("foo", "bar")

val underlyingList = mutableListOf("foo", "bar")
val listViaExtensionMethod = underlyingList.toImmutableList()

// changes to underlying collection to not change ImmutableList
underlyingList.add("baz")
assertEquals(3, underlyingList.size)
assertEquals(2, listViaExtensionMethod.size)

// cannot be converted to MutableList
assertThrows<Throwable> {
    (listViaConstructor as MutableList<String>)
}
```

### ImmutableMap

```kotlin
val mapViaConstructor = ImmutableMap("foo" to "bar")
val mapViaGlobalMethod = immutableMapOf("foo" to "bar")

val underlyingMap = mutableMapOf("foo" to "bar")
val mapViaExtensionMethod = underlyingMap.toImmutableMap()

// changes to underlying collection to not change ImmutableMap
underlyingMap.put("baz", "foo-bar")
assertEquals(2, underlyingMap.size)
assertEquals(1, mapViaExtensionMethod.size)

// cannot be converted to MutableMap
assertThrows<Throwable> {
    (mapViaConstructor as MutableMap<String, String>)
}
```

### ImmutableSet

```kotlin
val setViaConstructor = ImmutableSet("foo", "bar")
val setViaGlobalMethod = immutableSetOf("foo", "bar")

val underlyingSet = mutableSetOf("foo", "bar")
val setViaExtensionMethod = underlyingSet.toImmutableSet()

// changes to underlying collection to not change ImmutableSet
underlyingSet.add("baz")
assertEquals(3, underlyingSet.size)
assertEquals(2, setViaExtensionMethod.size)

// cannot be converted to MutableSet
assertThrows<Throwable> {
    (setViaConstructor as MutableSet<String>)
}
```

For more example see [KotlinExamples](src/jvmTest/kotlin/net/codinux/collections/KotlinExamples.kt).

### Java

```java
List<String> listViaConstructor = new ImmutableList<>("foo", "bar");
List<String> listViaGlobalMethod = net.codinux.collections.ImmutableCollectionExtensionKt.immutableListOf("foo", "bar");

Map<String, String> mapViaConstructor = new ImmutableMap<>(Collections.singletonMap("foo", "bar"));
Map<String, String> mapViaGlobalMethod = net.codinux.collections.ImmutableCollectionExtensionKt.immutableMapOf(Collections.singletonMap("foo", "bar"));

Set<String> setViaConstructor = new ImmutableSet<>("foo", "bar");
Set<String> setViaGlobalMethod = net.codinux.collections.ImmutableCollectionExtensionKt.immutableSetOf("foo", "bar");

// all Collections are immutable, mutable operations are not supported
assertThrows(UnsupportedOperationException.class, () -> listViaConstructor.add("baz"));
assertThrows(UnsupportedOperationException.class, () -> mapViaConstructor.put("baz", "foo-bar"));
assertThrows(UnsupportedOperationException.class, () -> setViaConstructor.add("baz"));
```

For more example see [JavaExamples](src/jvmTest/java/net/codinux/collections/JavaExamples.java).



## License

    Copyright 2023 codinux GmbH & Co. KG

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
