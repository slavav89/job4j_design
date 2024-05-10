package ru.job4j.ood.srp;

import java.util.List;

/**
 * Реализации интерфейса Shop будут уметь:
 * 1. генерировать список продуктов (метод List<T> getListProducts());
 * 2. добавлять новый продукт (метод void addNewProduct());
 * 3. печатать список прдуктов (метод String printProducts());
 * Нарушается принцип SRP в связи с тем, что реализации интерфейса Shop
 * будут иметь различный функционал т.к. будут уметь и печатать,
 * и добавлять, и генерировать списки.
 */
public interface Shop<T> {
    List<T> getListProducts();

    void addNewProduct();

    String printProducts();
}
