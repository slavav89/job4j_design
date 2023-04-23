package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenThreeAddAndDeleteAndReplaceThenFirstNumberIs4() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.add(new Role("2", 2));
        store.add(new Role("3", 3));
        store.delete("1");
        store.replace("2", new Role("2", 4));
        Role result = store.findById("2");
        assertThat(result.getNumber()).isEqualTo(4)
                .isGreaterThan(3)
                .isLessThan(5)
                .isPositive()
                .isNotZero()
                .isEven();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.add(new Role("2", 2));
        store.add(new Role("3", 3));
        boolean result = store.delete("1");
        assertThat(result).isTrue()
                .isNotNull();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.add(new Role("2", 2));
        store.add(new Role("3", 3));
        boolean result = store.delete("4");
        assertThat(result).isFalse()
                .isNotNull();
    }

    @Test
    void whenAddAndFindThenNumberIs3() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.add(new Role("2", 2));
        store.add(new Role("3", 3));
        Role result = store.findById("3");
        assertThat(result.getNumber()).isEqualTo(3)
                .isNotZero()
                .isPositive()
                .isGreaterThan(2)
                .isLessThan(4);
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.add(new Role("2", 2));
        store.add(new Role("3", 3));
        Role result = store.findById("4");
        assertThat(result).isNull();
    }
}