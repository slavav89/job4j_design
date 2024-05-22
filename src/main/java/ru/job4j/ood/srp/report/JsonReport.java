package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.JsonDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private final Store store;
    private final Gson builder;

    public JsonReport(Store store) {
        this.store = store;
        this.builder = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(GregorianCalendar.class, new JsonDateTimeParser())
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return builder.toJson(store.findBy(filter));
    }
}