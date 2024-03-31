package ru.job4j.gc.prof;

public enum TypeSort {
    CREATE_ARRAY(1),
    MERGE_SORT(2),
    BUBBLE_SORT(3),
    INSERT_SORT(4);
    private int number;
    TypeSort(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}
