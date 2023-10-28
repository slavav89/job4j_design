package ru.job4j.io.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "bus")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bus {
    @XmlAttribute
    private boolean passenger;
    @XmlAttribute
    private int seat;
    @XmlAttribute
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
        /** Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Bus.class);
        /** Подключаем сериализатор*/
        Marshaller marshaller = context.createMarshaller();
        /** Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(bus, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Bus result = (Bus) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
