package ru.job4j.ood.ocp;

public class Degree {
    /**
     * Данный класс нарушает принцип OCP из-за того,
     * что он не предоставляет гибкости для расширения
     * новыми типами операций над числами.
     * Например, для вычисления числа в четвертой и последующих степенях,
     * придется вносить изменения в класс.
     */
    public static int square(int i) {
        return i * i;
    }

    public static int cube(int i) {
        return i * i * i;
    }

    public static void main(String[] args) {
        System.out.println(square(2));
        System.out.println(cube(3));
    }
}
