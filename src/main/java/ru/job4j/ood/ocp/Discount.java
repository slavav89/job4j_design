package ru.job4j.ood.ocp;

public class Discount {
    /**
     * Данный класс нарушает принцип OCP,
     * так как для добавления новых сценариев скидок
     * придется изменять существующий код класса Discount,
     * а не расширять его через добавление новых классов или методов.
     */
    public double finalPrise(double prise, int amountGoods) {
        return switch (amountGoods) {
            case 2 -> prise * 0.9;
            case 3 -> prise * 0.8;
            case 4 -> prise * 0.7;
            default -> prise;
        };
    }

    public static void main(String[] args) {
        Discount discount = new Discount();
        var costAllGoods = 100;
        var amountGoods = 3;
        double prise = discount.finalPrise(costAllGoods, amountGoods);
        System.out.println("Окончательная стоимость составляет: " + prise);
    }
}
