package net.codinux.kotlin.collections

class ImmutableList<E>(elements: List<E>) : List<E> {

    constructor(elements: Iterable<E>) : this(elements.toList())

    constructor(vararg elements: E) : this(elements.asList())


    private val source: List<E> = ArrayList(elements) // make a copy so that changes to source don't change state of this instance


    override val size = this.source.size

    override fun isEmpty() = source.isEmpty()

    override fun get(index: Int) = source.get(index)

    override fun indexOf(element: E) = source.indexOf(element)

    override fun lastIndexOf(element: E) = source.lastIndexOf(element)

    override fun contains(element: E) = source.contains(element)

    override fun containsAll(elements: Collection<E>) = source.containsAll(elements)

    override fun iterator() = source.iterator()

    override fun listIterator() = source.listIterator()

    override fun listIterator(index: Int) = source.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = source.subList(fromIndex, toIndex)

    override fun toString() = source.toString()


    // adds a class method for languages that don't support extension methods like Java, ...
    fun toMutableList(): MutableList<E> = (this as Collection<E>).toMutableList()

}