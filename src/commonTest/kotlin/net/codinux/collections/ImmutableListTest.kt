package net.codinux.collections

import net.codinux.collections.CollectionsTestData.CountElements
import net.codinux.collections.CollectionsTestData.ListTestData
import net.codinux.collections.CollectionsTestData.forAllElements
import kotlin.test.*

class ImmutableListTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableList("one", "two")

        assertFails {
            underTest as MutableList<String>
        }
    }

    @Test
    fun toMutableList() {
        val underTest = ImmutableList("one", "two")

        val result = underTest.toMutableList()
        result.add("three")

        assertEquals(3, result.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList_Iterable() {
        val source = ArrayDeque(setOf("one", "two"))
        val underTest = ImmutableList(source as Iterable<String>)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList_List() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableList(source)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList_Set() {
        val source = mutableSetOf("one", "two")
        val underTest = ImmutableList(source)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun toImmutableList() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = source.toImmutableList()

        assertIs<ImmutableList<String>>(result)
        assertEquals(2, result.size)
    }

    @Test
    fun immutableListOf_Iterable() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = immutableListOf(source)

        assertIs<ImmutableList<String>>(result)
        assertEquals(2, result.size)
    }

    @Test
    fun immutableListOf_Vararg() {
        val result = immutableListOf("one", "two")

        assertIs<ImmutableList<String>>(result)
        assertEquals(2, result.size)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableList(ListTestData)

        assertEquals(CountElements, underTest.size)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableList(emptyList<String>())

        assertTrue(underTest.isEmpty())
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableList(ListTestData)

        assertTrue(underTest.isNotEmpty())
    }

    @Test
    fun iterationOrderRemains() {
        val underTest = ImmutableList(ListTestData)

        forAllElements { index ->
            assertEquals(underTest[index], ListTestData[index])
        }
    }

    @Test
    fun indexOf() {
        val underTest = ImmutableList(ListTestData)

        forAllElements { index ->
            assertEquals(index, underTest.indexOf(ListTestData[index]))
        }
    }

    @Test
    fun contains() {
        val underTest = ImmutableList(ListTestData)

        forAllElements { index ->
            assertTrue(underTest.contains(ListTestData[index]))
        }
    }

    @Test
    fun containsAll() {
        val underTest = ImmutableList(ListTestData)

        assertTrue(underTest.containsAll(ListTestData))
    }

}