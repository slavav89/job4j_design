package ru.job4j.cache.menu;

public enum Status {
    CACHE_DIR("Введите кэшируемую директорию: "),
    NAME_FILE("Введите название файла: "),
    EXIT("Конец работы"),
    LOAD_CACHE("1"),
    GET_CACHE("2"),
    MENU("""
            Введите:
                1 - для загрузки содержимого файла в кэш,
                2 - для получения содержимого файла из кэша,
                любое другое число для выхода.
            """);
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
