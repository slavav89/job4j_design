package ru.job4j.gc.prof;

public enum Status {
    BEGIN(" Начало сортировки - "),

    END(" Конец сортировки - "),

    SELECT("Введите количество элементов"),

    ARRAY_INFO("Массив создан и заполнен случайными значениями от 1 до "),

    MENU("""
                Введите 1 для создание массива.
                Введите 2, чтобы начать сортировку слиянием.
                Введите 3, чтобы начать сортировку пузырьком.
                Введите 4,чтобы начать сортировку вставками.
                Введите любое другое число для выхода.
            """);
    private String status;
    Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
