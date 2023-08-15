package net.codinux.collections

import net.codinux.collections.CollectionsTestData.CountElements
import net.codinux.collections.CollectionsTestData.MapTestData
import net.codinux.collections.CollectionsTestData.forAllElements
import kotlin.test.*

class ImmutableMapTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableMap("one" to "two")

        assertFails {
            underTest as MutableMap<String, String>
        }
    }

    @Test
    fun toMutableMap() {
        val underTest = ImmutableMap("one" to "two")

        val result = underTest.toMutableMap()
        result.put("three", "four")

        assertEquals(2, result.size)
        assertEquals(1, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableMap_IterableOfPairs() {
        val source = mutableListOf("one" to "two")
        val underTest = ImmutableMap(source)

        assertEquals(1, source.size)
        assertEquals(1, underTest.size)

        source.add("three" to "four")

        assertEquals(2, source.size)
        assertEquals(1, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableMap_Map() {
        val source = mutableMapOf("one" to "two")
        val underTest = ImmutableMap(source)

        assertEquals(1, source.size)
        assertEquals(1, underTest.size)

        source.put("three", "four")

        assertEquals(2, source.size)
        assertEquals(1, underTest.size)
    }

    @Test
    fun toImmutableMap_Map() {
        val source = mapOf("one" to "two")

        val result = source.toImmutableMap()

        assertIs<ImmutableMap<String, String>>(result)
        assertEquals(1, result.size)
    }

    @Test
    fun toImmutableMap_Pair() {
        val source = "one" to "two"

        val result = source.toImmutableMap()

        assertIs<ImmutableMap<String, String>>(result)
        assertEquals(1, result.size)
    }

    @Test
    fun toImmutableMap_IterableOfPairs() {
        val source = listOf("one" to "two", "three" to "four")

        val result = source.toImmutableMap()

        assertIs<ImmutableMap<String, String>>(result)
        assertEquals(2, result.size)
    }

    @Test
    fun immutableMapOf_Iterable() {
        val source = mapOf("one" to "two")

        val result = immutableMapOf(source)

        assertIs<ImmutableMap<String, String>>(result)
        assertEquals(1, result.size)
    }

    @Test
    fun immutableMapOf_Vararg() {
        val result = immutableMapOf("one" to "two", "three" to "four")

        assertIs<ImmutableMap<String, String>>(result)
        assertEquals(2, result.size)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableMap(MapTestData)

        assertEquals(CountElements, underTest.size)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableMap(emptyMap<String, String>())

        assertTrue(underTest.isEmpty())
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableMap(MapTestData)

        assertTrue(underTest.isNotEmpty())
    }

    @Test
    fun keys() {
        val underTest = ImmutableMap(MapTestData)

        assertContentEquals(listOf(0, 1 ,2, 3, 4, 5, 6, 7, 8, 9, 10, 11), underTest.keys)
    }

    @Test
    fun values() {
        val underTest = ImmutableMap(MapTestData)

        assertContentEquals(listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"), underTest.values)
    }

    @Test
    fun get() {
        val underTest = ImmutableMap(MapTestData)

        forAllElements { index ->
            assertEquals(index.toString(), underTest[index])
        }
    }

    @Test
    fun containsKey() {
        val underTest = ImmutableMap(MapTestData)

        forAllElements { index ->
            assertTrue(underTest.containsKey(index))
        }
    }

    @Test
    fun containsValue() {
        val underTest = ImmutableMap(MapTestData)

        forAllElements { index ->
            assertTrue(underTest.containsValue(index.toString()))
        }
    }

}