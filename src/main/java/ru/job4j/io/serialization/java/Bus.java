package ru.job4j.io.serialization.java;

import org.json.JSONObject;

import java.util.Arrays;

public class Bus {

    private boolean passenger;
    private int seat;
    private String nameDriver;
    private Contact contactDriver;
    private String[] namePassengers;

    public Bus() {
    }

    public Bus(boolean passenger, int seat, Contact contactDriver, String nameDriver, String[] namePassengers) {
        this.passenger = passenger;
        this.seat = seat;
        this.contactDriver = contactDriver;
        this.nameDriver = nameDriver;
        this.namePassengers = namePassengers;
    }

    public boolean getPassenger() {
        return passenger;
    }

    public int getSeat() {
        return seat;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public Contact getContactDriver() {
        return contactDriver;
    }

    public String[] getNamePassengers() {
        return namePassengers;
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

    public static void main(String[] args) throws Exception {
        Bus bus = new Bus(true, 18,
                new Contact(777, "8 999 777 22 33"), "Ivan", new String[]{"Andrey, Igor"});
        JSONObject jsonContact = new JSONObject();
        jsonContact.put("zipCode", bus.getContactDriver().getZipCode());
        jsonContact.put("phone", bus.getContactDriver().getPhone());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("passenger", bus.getPassenger());
        jsonObject.put("seat", bus.getSeat());
        jsonObject.put("nameDriver", bus.getNameDriver());
        jsonObject.put("contactDriver", jsonContact);
        jsonObject.put("namePassengers", bus.getNamePassengers());
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(bus).toString());
    }
}
