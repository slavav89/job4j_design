package ru.job4j.io.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 8000);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
        writer.write("Get\n");
        writer.flush();

        String message = reader.readLine();
        System.out.println(message);
        writer.close();
        reader.close();
        client.close();
    }
}
