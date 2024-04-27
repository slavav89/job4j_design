package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {

    /**
     * При покупке нового билета, сравниваем
     * с созданным билетом.
     */
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    /**
     * При добавлении одного сеанса, проверяем
     * что он содержится в списке кинотеатра.
     */
    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    /**
     * При добавлении одного сеанса, проверяем
     * что в списке кинотеатра он один.
     */
    @Test
    public void whenAddSessionThenSizeIs1() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions.size()).isEqualTo(1);
    }

    /**
     * Попытка покупки билета на несуществующий ряд,
     * выдает исключение.
     */
    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Попытка покупки билета на несуществующее место,
     * выдает исключение.
     */
    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, -1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Попытка покупки билета на прошедший сеанс,
     * выдает исключение.
     */
    @Test
    public void whenBuyOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2024, 03, 01);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Попытка покупки билета на несвободное место,
     * выдает исключение.
     */
    @Test
    public void whenBuyOccupiedPlaceThenGetException() {
        Account account1 = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account1, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account2, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }
}