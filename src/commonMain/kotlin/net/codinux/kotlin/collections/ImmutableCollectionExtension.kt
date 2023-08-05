package net.codinux.kotlin.collections


fun <T> Iterable<T>.toImmutableList() = ImmutableList(this)

fun <T> Iterable<T>.toImmutableSet() = ImmutableSet(this)

fun <K, V> Map<K, V>.toImmutableMap() = ImmutableMap(this)

fun <K, V> Pair<K, V>.toImmutableMap() = ImmutableMap(this)

fun <K, V> Iterable<Pair<K, V>>.toImmutableMap() = ImmutableMap(this)