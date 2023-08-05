package net.codinux.kotlin.collections

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import net.codinux.kotlin.collections.CollectionsTestData.CountElements
import net.codinux.kotlin.collections.CollectionsTestData.ListTestData
import net.codinux.kotlin.collections.CollectionsTestData.forAllElements
import kotlin.test.Test

class ImmutableListTest {

    @Test
    fun isImmutable() {
        val underTest = ImmutableList("one", "two")

        shouldThrowAny {
            underTest as MutableList<String>
        }
    }

    @Test
    fun toMutableList() {
        val underTest = ImmutableList("one", "two")

        val result = underTest.toMutableList()
        result.add("three")

        result.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList_Iterable() {
        val source = ArrayDeque(setOf("one", "two"))
        val underTest = ImmutableList(source as Iterable<String>)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList_List() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableList(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList_Set() {
        val source = mutableSetOf("one", "two")
        val underTest = ImmutableList(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
    }

    @Test
    fun toImmutableList() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = source.toImmutableList()

        result.shouldBeInstanceOf<ImmutableList<String>>()
        result.shouldHaveSize(2)
    }

    @Test
    fun immutableListOf_Iterable() {
        val source: Iterable<String> = ArrayDeque(setOf("one", "two"))

        val result = immutableListOf(source)

        result.shouldBeInstanceOf<ImmutableList<String>>()
        result.shouldHaveSize(2)
    }

    @Test
    fun immutableListOf_Vararg() {
        val result = immutableListOf("one", "two")

        result.shouldBeInstanceOf<ImmutableList<String>>()
        result.shouldHaveSize(2)
    }


    @Test
    fun getSize() {
        val underTest = ImmutableList(ListTestData)

        underTest.shouldHaveSize(CountElements)
    }

    @Test
    fun isEmpty() {
        val underTest = ImmutableList(emptyList<String>())

        underTest.isEmpty().shouldBeTrue()
    }

    @Test
    fun isNotEmpty() {
        val underTest = ImmutableList(ListTestData)

        underTest.isNotEmpty().shouldBeTrue()
    }

    @Test
    fun iterationOrderRemains() {
        val underTest = ImmutableList(ListTestData)

        forAllElements { index ->
            ListTestData[index].shouldBe(underTest[index])
        }
    }

    @Test
    fun indexOf() {
        val underTest = ImmutableList(ListTestData)

        forAllElements { index ->
            underTest.indexOf(ListTestData[index]).shouldBe(index)
        }
    }

    @Test
    fun contains() {
        val underTest = ImmutableList(ListTestData)

        forAllElements { index ->
            underTest.contains(ListTestData[index]).shouldBeTrue()
        }
    }

    @Test
    fun containsAll() {
        val underTest = ImmutableList(ListTestData)

        underTest.containsAll(ListTestData).shouldBeTrue()
    }

}