package net.codinux.collections


fun <T> Iterable<T>.toImmutableList() = ImmutableList(this)

fun <T> Iterable<T>.toImmutableSet() = ImmutableSet(this)

fun <K, V> Map<K, V>.toImmutableMap() = ImmutableMap(this)

fun <K, V> Pair<K, V>.toImmutableMap() = ImmutableMap(this)

fun <K, V> Iterable<Pair<K, V>>.toImmutableMap() = ImmutableMap(this)


fun <E> immutableListOf(elements: Iterable<E>) = ImmutableList(elements)

fun <E> immutableListOf(vararg elements: E) = ImmutableList(*elements)

fun <K, V> immutableMapOf(map: Map<K, V>) = ImmutableMap(map)

fun <K, V> immutableMapOf(vararg entries: Pair<K, V>) = ImmutableMap(*entries)

fun <E> immutableSetOf(elements: Iterable<E>) = ImmutableSet(elements)

fun <E> immutableSetOf(vararg elements: E) = ImmutableSet(*elements)