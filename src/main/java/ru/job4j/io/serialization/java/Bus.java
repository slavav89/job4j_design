package ru.job4j.io.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Bus {
    private final boolean passenger;
    private final int seat;
    private final Contact contactDriver;
    private final String nameDriver;
    private final String[] namePassengers;

    public Bus(boolean passenger, int seat, Contact contactDriver, String nameDriver, String[] namePassengers) {
        this.passenger = passenger;
        this.seat = seat;
        this.contactDriver = contactDriver;
        this.nameDriver = nameDriver;
        this.namePassengers = namePassengers;
    }

    @Override
    public String toString() {
        return "Bus{"
                + "passenger=" + passenger
                + ", seat=" + seat
                + ", contactDriver=" + contactDriver
                + ", nameDriver='" + nameDriver + '\''
                + ", namePassengers=" + Arrays.toString(namePassengers)
                + '}';
    }

    public static void main(String[] args) {
        final Bus bus = new Bus(true, 18,
                new Contact(777, "8 999 777 22 33"), "Ivan", new String[]{"Andrey, Igor"});
        final Gson gson = new GsonBuilder().create();
        String busJson = gson.toJson(bus);
        System.out.println(busJson);
        final Bus busFromJson = gson.fromJson(busJson, Bus.class);
        System.out.println(busFromJson);
    }
}
