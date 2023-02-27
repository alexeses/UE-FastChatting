package com.github.alexeses.model;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {

        ServerSocket server;
        QueueMensajes msg = new QueueMensajes();
        ArrayList<Cliente> users = new ArrayList<>();
        ThreadEscribir writeMethod = new ThreadEscribir(msg, users);

        writeMethod.start();

        try {
            server = new ServerSocket(6125);
            while (true) {
                Socket socket = server.accept();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                String x = (String) in.readObject();
                while (true) {
                    try {
                        Cliente usuario = new Cliente(x, socket, msg);
                        int numUsers = users.size();
                        System.out.println("> User " + x + " connected. Number of users connected: " + numUsers);
                        users.add(usuario);
                        usuario.setUsers(users);
                        usuario.start();
                        break;
                    } catch (Exception e) {
                        System.out.println("Error 0202: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error 0909: " + e.getMessage());
        }
    }
}
