package net.codinux.collections

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import net.codinux.collections.CollectionsTestData.CountElements
import net.codinux.collections.CollectionsTestData.SetTestData
import net.codinux.collections.CollectionsTestData.forAllElements
import kotlin.test.Test

class ImmutableCollectionTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableCollection("one", "two")

        shouldThrowAny {
            underTest as MutableList<String>
        }
    }

    @Test
    fun toMutableCollection() {
        val underTest = ImmutableCollection("one", "two")

        val result = underTest.toMutableCollection()
        result.add("three")

        result.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableCollection_Iterable() {
        val source = ArrayDeque(setOf("one", "two"))
        val underTest = ImmutableCollection(source as Iterable<String>)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableCollection_List() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableCollection(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableCollection_Set() {
        val source = mutableSetOf("one", "two")
        val underTest = ImmutableCollection(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableCollection(SetTestData)

        underTest.shouldHaveSize(CountElements)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableCollection(emptySet<String>())

        underTest.isEmpty().shouldBeTrue()
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableCollection(SetTestData)

        underTest.isNotEmpty().shouldBeTrue()
    }

    @Test
    fun indexOf() {
        val underTest = ImmutableCollection(SetTestData)

        forAllElements { index ->
            underTest.indexOf(index.toString()).shouldBe(index)
        }
    }

    @Test
    fun contains() {
        val underTest = ImmutableCollection(SetTestData)

        forAllElements { index ->
            underTest.contains(index.toString()).shouldBeTrue()
        }
    }

    @Test
    fun containsAll() {
        val underTest = ImmutableCollection(SetTestData)

        underTest.containsAll(SetTestData).shouldBeTrue()
    }

}