package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkNavigationArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .allSatisfy(s -> {
                    assertThat(s.length()).isLessThan(7);
                    assertThat(s.length()).isGreaterThan(3);
                })
                .anySatisfy(s -> assertThat(s).isEqualTo("four"));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .startsWith("first")
                .endsWith("five")
                .containsSequence("second", "three")
                .containsExactly("first", "second", "three", "four", "five")
                .doesNotContain("six", "seven")
                .element(0).isNotNull();
    }

    @Test
    void checkNavigationList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .allMatch(s -> s.length() < 7)
                .anyMatch(s -> s.length() > 5)
                .noneMatch(s -> s.length() < 4)
                .first().isEqualTo("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("six", "seven", "eight", "six", "nine", "ten");
        assertThat(set)
                .isNotNull()
                .hasSize(5)
                .containsExactlyInAnyOrder("six", "seven", "eight", "nine", "ten");
    }

    @Test
    void checkNavigationSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("six", "seven", "eight", "six", "nine", "ten");
        assertThat(set)
                .filteredOn(s -> s.length() < 4)
                .hasSize(2);
        assertThat(set).filteredOnAssertions(s -> assertThat(s.length()).isGreaterThan(4))
                .hasSize(2);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .isNotNull()
                .hasSize(5)
                .containsKeys("first", "second", "three", "four", "five")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKey("six")
                .doesNotContainValue(5)
                .containsEntry("five", 4);
    }
}