package net.codinux.collections

import net.codinux.collections.CollectionsTestData.CountElements
import net.codinux.collections.CollectionsTestData.SetTestData
import net.codinux.collections.CollectionsTestData.forAllElements
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class ImmutableCollectionTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableCollection("one", "two")

        assertFails {
            @Suppress("UNCHECKED_CAST")
            underTest as MutableList<String>
        }
    }

    @Test
    fun toMutableCollection() {
        val underTest = ImmutableCollection("one", "two")

        val result = underTest.toMutableCollection()
        result.add("three")

        assertEquals(3, result.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableCollection_Iterable() {
        val source = ArrayDeque(setOf("one", "two"))
        val underTest = ImmutableCollection(source as Iterable<String>)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableCollection_List() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableCollection(source)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableCollection_Set() {
        val source = mutableSetOf("one", "two")
        val underTest = ImmutableCollection(source)

        assertEquals(2, source.size)
        assertEquals(2, underTest.size)

        source.add("three")

        assertEquals(3, source.size)
        assertEquals(2, underTest.size)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableCollection(SetTestData)

        assertEquals(CountElements, underTest.size)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableCollection(emptySet<String>())

        assertTrue(underTest.isEmpty())
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableCollection(SetTestData)

        assertTrue(underTest.isNotEmpty())
    }

    @Test
    fun indexOf() {
        val underTest = ImmutableCollection(SetTestData)

        forAllElements { index ->
            assertEquals(index, underTest.indexOf(index.toString()))
        }
    }

    @Test
    fun contains() {
        val underTest = ImmutableCollection(SetTestData)

        forAllElements { index ->
            assertTrue(underTest.contains(index.toString()))
        }
    }

    @Test
    fun containsAll() {
        val underTest = ImmutableCollection(SetTestData)

        assertTrue(underTest.containsAll(SetTestData))
    }

}