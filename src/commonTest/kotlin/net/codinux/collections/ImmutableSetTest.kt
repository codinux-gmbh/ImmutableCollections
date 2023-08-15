package net.codinux.collections

import net.codinux.collections.CollectionsTestData.CountElements
import net.codinux.collections.CollectionsTestData.SetTestData
import net.codinux.collections.CollectionsTestData.forAllElements
import kotlin.test.*

class ImmutableSetTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableSet("one", "two")

        assertFails {
            underTest as MutableSet<String>
        }
    }

    @Test
    fun toMutableSet() {
        val underTest = ImmutableSet("one", "two")

        val result = underTest.toMutableSet()
        result.add("three")

        assertEquals(3, result.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableSet_Iterable() {
        val source = ArrayDeque(setOf("one", "two"))
        val underTest = ImmutableSet(source as Iterable<String>)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableSet_List() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableSet(source)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableSet_Set() {
        val source = mutableSetOf("one", "two")
        val underTest = ImmutableSet(source)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun toImmutableSet() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = source.toImmutableSet()

        assertIs<ImmutableSet<String>>(result)
        assertEquals(2, result.size)
    }

    @Test
    fun immutableSetOf_Iterable() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = immutableSetOf(source)

        assertIs<ImmutableSet<String>>(result)
        assertEquals(2, result.size)
    }

    @Test
    fun immutableSetOf_Vararg() {
        val result = immutableSetOf("one", "two")

        assertIs<ImmutableSet<String>>(result)
        assertEquals(2, result.size)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableSet(SetTestData)

        assertEquals(CountElements, underTest.size)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableSet(emptySet<String>())

        assertTrue(underTest.isEmpty())
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableSet(SetTestData)

        assertTrue(underTest.isNotEmpty())
    }

    @Test
    fun indexOf() {
        val underTest = ImmutableSet(SetTestData)

        forAllElements { index ->
            assertEquals(index, underTest.indexOf(index.toString()))
        }
    }

    @Test
    fun contains() {
        val underTest = ImmutableSet(SetTestData)

        forAllElements { index ->
            assertTrue(underTest.contains(index.toString()))
        }
    }

    @Test
    fun containsAll() {
        val underTest = ImmutableSet(SetTestData)

        assertTrue(underTest.containsAll(SetTestData))
    }

}