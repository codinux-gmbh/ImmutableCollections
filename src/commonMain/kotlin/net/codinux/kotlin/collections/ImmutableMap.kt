package net.codinux.kotlin.collections

class ImmutableMap<K, V>(private val wrapped: Map<K, V>) : Map<K, V> {

    constructor(vararg entries: Pair<K, V>) : this(entries.toMap())


    override val size = wrapped.size

    override val keys = wrapped.keys

    override val values = wrapped.values

    override val entries = wrapped.entries


    override fun isEmpty() = wrapped.isEmpty()

    override fun containsKey(key: K) = wrapped.containsKey(key)

    override fun containsValue(value: V) = wrapped.containsValue(value)

    override fun get(key: K) = wrapped.get(key)

    override fun toString() = wrapped.toString()


    // adds a class method for languages that don't support extension methods like Java, ...
    fun toMutableMap(): MutableMap<K, V> = (this as Map<K, V>).toMutableMap()

}