package net.codinux.kotlin.collections

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import net.codinux.kotlin.collections.CollectionsTestData.CountElements
import net.codinux.kotlin.collections.CollectionsTestData.SetTestData
import net.codinux.kotlin.collections.CollectionsTestData.forAllElements
import kotlin.test.Test

class ImmutableSetTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableSet("one", "two")

        shouldThrowAny {
            underTest as MutableSet<String>
        }
    }

    @Test
    fun toMutableSet() {
        val underTest = ImmutableSet("one", "two")

        val result = underTest.toMutableSet()
        result.add("three")

        result.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableSet_Iterable() {
        val source = ArrayDeque(setOf("one", "two"))
        val underTest = ImmutableSet(source as Iterable<String>)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableSet_List() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableSet(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableSet_Set() {
        val source = mutableSetOf("one", "two")
        val underTest = ImmutableSet(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun toImmutableSet() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = source.toImmutableSet()

        result.shouldBeInstanceOf<ImmutableSet<String>>()
        result.shouldHaveSize(2)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableSet(SetTestData)

        underTest.shouldHaveSize(CountElements)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableSet(emptySet<String>())

        underTest.isEmpty().shouldBeTrue()
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableSet(SetTestData)

        underTest.isNotEmpty().shouldBeTrue()
    }

    @Test
    fun indexOf() {
        val underTest = ImmutableSet(SetTestData)

        forAllElements { index ->
            underTest.indexOf(index.toString()).shouldBe(index)
        }
    }

    @Test
    fun contains() {
        val underTest = ImmutableSet(SetTestData)

        forAllElements { index ->
            underTest.contains(index.toString()).shouldBeTrue()
        }
    }

    @Test
    fun containsAll() {
        val underTest = ImmutableSet(SetTestData)

        underTest.containsAll(SetTestData).shouldBeTrue()
    }

}