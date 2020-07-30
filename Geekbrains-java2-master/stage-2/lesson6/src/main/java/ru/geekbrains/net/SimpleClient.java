package ru.geekbrains.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8181)) {
            System.out.println("Client connected: " + socket.getLocalPort());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                Scanner scanner = new Scanner(System.in);
                out.writeUTF(scanner.next());
                String s = in.readUTF();
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
