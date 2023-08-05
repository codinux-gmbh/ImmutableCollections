package net.codinux.kotlin.collections;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.codinux.kotlin.collections.ImmutableCollectionExtensionKt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaExamples {

    @Test
    public void showUsageInJava() {
        List<String> listViaConstructor = new ImmutableList<>("one", "two");
        List<String> listViaExtensionMethod = immutableListOf("one", "two");

        assertEquals(2, listViaConstructor.size());

        Map<String, String> mapViaConstructor = new ImmutableMap<>(Collections.singletonMap("one", "two")); // TODO: why does the vararg Pair<K, V> constructor not work her?
        Map<String, String> mapViaExtensionMethod = immutableMapOf(Collections.singletonMap("one", "two"));

        assertEquals(1, mapViaConstructor.size());

        Set<String> setViaConstructor = new ImmutableSet<>("one", "two");
        Set<String> setViaExtensionMethod = immutableSetOf("one", "two");

        assertEquals(2, setViaConstructor.size());

        // all Collections are immutable, mutable operations are not supported
        assertThrows(UnsupportedOperationException.class, () -> listViaConstructor.add("three"));
        assertThrows(UnsupportedOperationException.class, () -> mapViaConstructor.put("three", "four"));
        assertThrows(UnsupportedOperationException.class, () -> setViaConstructor.add("three"));
    }

}
