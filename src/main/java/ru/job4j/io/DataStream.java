package ru.job4j.io;

import java.io.*;

public class DataStream {
    public static void main(String[] args) throws Exception {
        String path = "data/dataOutput.txt";
        String[] names = {"unit1", "unit2", "unit3"};
        int[] amount = {5, 7, 2};
        boolean[] checked = {true, false, true};
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
             DataInputStream in = new DataInputStream(new FileInputStream(path))) {
            for (int i = 0; i < names.length; i++) {
                out.writeUTF(names[i]);
                out.writeInt(amount[i]);
                out.writeBoolean(checked[i]);
            }
            while (true) {
                System.out.println("Наименование: " + in.readUTF()
                        + ", количество: " + in.readInt()
                        + ", проверен: " + in.readBoolean());
            }
        } catch (EOFException e) {
            System.out.println("Достигнут конец файла");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
