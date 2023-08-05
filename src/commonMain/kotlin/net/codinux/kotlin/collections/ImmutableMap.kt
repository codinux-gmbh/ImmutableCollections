package net.codinux.kotlin.collections

class ImmutableMap<K, V>(map: Map<K, V>) : Map<K, V> {

    constructor(vararg entries: Pair<K, V>) : this(entries.toMap())


    private val source: Map<K, V> = LinkedHashMap(map) // make a copy so that changes to source don't change state of this instance


    override val size = source.size

    override val keys = source.keys

    override val values = source.values

    override val entries = source.entries


    override fun isEmpty() = source.isEmpty()

    override fun containsKey(key: K) = source.containsKey(key)

    override fun containsValue(value: V) = source.containsValue(value)

    override fun get(key: K) = source.get(key)

    override fun toString() = source.toString()


    // adds a class method for languages that don't support extension methods like Java, ...
    fun toMutableMap(): MutableMap<K, V> = (this as Map<K, V>).toMutableMap()

}