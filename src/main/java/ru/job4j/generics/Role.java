package ru.job4j.generics;

public class Role extends Base {

    private final Integer number;

    public Role(String id, Integer number) {
        super(id);
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}
