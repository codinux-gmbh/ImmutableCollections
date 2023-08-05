package net.codinux.kotlin.collections

class ImmutableCollection<E>(elements: Collection<E>) : Collection<E> {

    constructor(elements: Iterable<E>) : this(elements.toList())

    constructor(vararg elements: E) : this(elements.asList())


    private val source: List<E> = ArrayList(elements) // make a copy so that changes to source don't change state of this instance


    override val size = source.size

    override fun isEmpty() = source.isEmpty()

    override fun contains(element: E) = source.contains(element)

    override fun containsAll(elements: Collection<E>) = source.containsAll(elements)

    override fun iterator() = source.iterator()

    override fun toString() = source.toString()


    // adds a class method for languages that don't support extension methods like Java, ...
    fun toMutableCollection(): MutableCollection<E> = (this as Collection<E>).toMutableList()

}