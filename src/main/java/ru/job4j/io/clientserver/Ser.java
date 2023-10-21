package ru.job4j.io.clientserver;

import java.io.*;
import java.net.*;

public class Ser {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8000);
        int count = 0;
        while (true) {
            Socket klientSocket = server.accept();
            System.out.println("Клиент подключен " + ++count);
            OutputStreamWriter writer = new OutputStreamWriter(klientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(klientSocket.getInputStream()));
            String str = reader.readLine();
            writer.write("HTTP/1.1 200 OK "
                    + "You client #" + count + str + "\r\n\r\n");
            writer.flush();
            reader.close();
            writer.close();
            klientSocket.close();
        }
    }
}
