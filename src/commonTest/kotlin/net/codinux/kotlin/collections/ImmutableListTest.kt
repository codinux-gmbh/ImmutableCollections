package net.codinux.kotlin.collections

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
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
    fun changesToUnderlyingSourceDoesNotChangeStateOfImmutableList() {
        val source = mutableListOf("one", "two")
        val underTest = ImmutableList(source)

        source.shouldHaveSize(2)
        underTest.shouldHaveSize(2)

        source.add("three")

        source.shouldHaveSize(3)
        underTest.shouldHaveSize(2)
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