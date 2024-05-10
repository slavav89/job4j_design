package ru.job4j.ood.srp;

/**
 * Реализации интерфейса User будут уметь:
 * 1. сохранять данные в БД (метод void save(T t));
 * 2. отправлять данные по E-mail (метод void sendEmail(String s));
 * Нарушается принцип SRP в связи с тем, что реализации интерфейса User
 * будут иметь различный функционал т.к. будут уметь сохранять данные
 * и отправлять данные по E-mail;
 */
public interface User<T> {
    void save(T t);

    void sendEmail(String s);
}
