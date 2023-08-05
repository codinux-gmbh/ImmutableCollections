package net.codinux.kotlin.collections

class ImmutableSet<E>(elements: Set<E>) : Set<E> {

    constructor(vararg elements: E) : this(elements.toSet())


    private val source: Set<E> = LinkedHashSet(elements) // make a copy so that changes to source don't change state of this instance


    override val size = source.size

    override fun isEmpty() = source.isEmpty()

    override fun contains(element: E) = source.contains(element)

    override fun containsAll(elements: Collection<E>) = source.containsAll(elements)

    override fun iterator() = source.iterator()

    override fun toString() = source.toString()


    // adds a class method for languages that don't support extension methods like Java, ...
    fun toMutableSet(): MutableSet<E> = (this as Set<E>).toMutableSet()

}