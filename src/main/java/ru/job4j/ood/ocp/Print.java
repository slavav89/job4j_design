package ru.job4j.ood.ocp;

import java.util.List;

public class Print {
    /**
     * Этот класс нарушает принцип открытости/закрытости.
     * В данном случае, при добавлении нового типа Print3,
     * потребуется изменять существующий код.
     */
    public static void main(String[] args) {
        List list = List.of(new Print1(), new Print2(), new Print1());
        for (var print : list) {
            if (print.getClass() == Print1.class) {
                ((Print1) print).print1();
            } else {
                ((Print2) print).print2();
            }
        }
    }
}

class Print1 {

    public void print1() {
        System.out.println("print1");
    }
}

class Print2 {

    public void print2() {
        System.out.println("print2");
    }
}