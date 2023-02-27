package com.github.alexeses.model;

import java.net.Socket;
import java.util.ArrayList;

public class Cliente extends Thread {
    
    private String name;
    private Socket socket;
    private QueueMensajes queue;
    private ArrayList<Cliente> users;

    public Cliente(String name, Socket socket, QueueMensajes queue, boolean isFirstTime) {
        this.name = name;
        this.socket = socket;
        this.queue = queue;
        if (isFirstTime) {
            this.queue.addMensaje(name + ", bienvenido al chat");
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUsers(ArrayList<Cliente> users) {
        this.users = users;
    }

    @Override
    public void run() {
        Listener listen = new Listener(this.socket, this.name, this.queue);
        listen.start();
    }

}
