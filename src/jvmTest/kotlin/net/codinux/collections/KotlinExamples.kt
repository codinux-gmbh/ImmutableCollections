package net.codinux.collections

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinExamples {

    @Test
    fun immutableList() {
        val listViaConstructor = ImmutableList("foo", "bar")
        val listViaGlobalMethod = immutableListOf("foo", "bar")

        val underlyingList = mutableListOf("foo", "bar")
        val listViaExtensionMethod = underlyingList.toImmutableList()

        assertEquals(2, listViaConstructor.size)
        assertEquals(2, listViaGlobalMethod.size)
        assertEquals(2, listViaExtensionMethod.size)

        // changes to underlying collection to not change ImmutableList
        underlyingList.add("baz")
        assertEquals(3, underlyingList.size)
        assertEquals(2, listViaExtensionMethod.size)

        // cannot be converted to MutableList
        assertThrows<Throwable> {
            (listViaConstructor as MutableList<String>)
        }
    }

    @Test
    fun immutableMap() {
        val mapViaConstructor = ImmutableMap("foo" to "bar")
        val mapViaGlobalMethod = immutableMapOf("foo" to "bar")

        val underlyingMap = mutableMapOf("foo" to "bar")
        val mapViaExtensionMethod = underlyingMap.toImmutableMap()

        assertEquals(1, mapViaConstructor.size)
        assertEquals(1, mapViaGlobalMethod.size)
        assertEquals(1, mapViaExtensionMethod.size)

        // changes to underlying collection to not change ImmutableMap
        underlyingMap.put("baz", "foo-bar")
        assertEquals(2, underlyingMap.size)
        assertEquals(1, mapViaExtensionMethod.size)

        // cannot be converted to MutableMap
        assertThrows<Throwable> {
            (mapViaConstructor as MutableMap<String, String>)
        }
    }

    @Test
    fun immutableSet() {
        val setViaConstructor = ImmutableSet("foo", "bar")
        val setViaGlobalMethod = immutableSetOf("foo", "bar")

        val underlyingSet = mutableSetOf("foo", "bar")
        val setViaExtensionMethod = underlyingSet.toImmutableSet()

        assertEquals(2, setViaConstructor.size)
        assertEquals(2, setViaGlobalMethod.size)
        assertEquals(2, setViaExtensionMethod.size)

        // changes to underlying collection to not change ImmutableSet
        underlyingSet.add("baz")
        assertEquals(3, underlyingSet.size)
        assertEquals(2, setViaExtensionMethod.size)

        // cannot be converted to MutableSet
        assertThrows<Throwable> {
            (setViaConstructor as MutableSet<String>)
        }
    }

}