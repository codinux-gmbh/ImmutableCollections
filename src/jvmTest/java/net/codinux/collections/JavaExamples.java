package net.codinux.collections;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaExamples {

    @Test
    public void immutableList() {
        List<String> listViaConstructor = new ImmutableList<>("foo", "bar");
        List<String> listViaGlobalMethod = net.codinux.collections.ImmutableCollectionExtensionKt.immutableListOf("foo", "bar");

        assertEquals(2, listViaConstructor.size());

        // all Collections are immutable, mutable operations are not supported
        assertThrows(UnsupportedOperationException.class, () -> listViaConstructor.add("baz"));
    }

    @Test
    public void immutableMap() {
        Map<String, String> mapViaConstructor = new ImmutableMap<>(Collections.singletonMap("foo", "bar")); // TODO: why does the vararg Pair<K, V> constructor not work her?
        Map<String, String> mapViaGlobalMethod = net.codinux.collections.ImmutableCollectionExtensionKt.immutableMapOf(Collections.singletonMap("foo", "bar"));

        assertEquals(1, mapViaConstructor.size());

        // all Collections are immutable, mutable operations are not supported
        assertThrows(UnsupportedOperationException.class, () -> mapViaConstructor.put("baz", "foo-bar"));
    }

    @Test
    public void immutableSet() {
        Set<String> setViaConstructor = new ImmutableSet<>("foo", "bar");
        Set<String> setViaGlobalMethod = net.codinux.collections.ImmutableCollectionExtensionKt.immutableSetOf("foo", "bar");

        assertEquals(2, setViaConstructor.size());

        // all Collections are immutable, mutable operations are not supported
        assertThrows(UnsupportedOperationException.class, () -> setViaConstructor.add("baz"));
    }

}
