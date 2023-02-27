package com.github.alexeses.model;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener extends Thread{
    
    private Socket socket;
    private String user;
    private QueueMensajes queueMsg;
    private ObjectInputStream in;

    public Listener(Socket socket, String user, QueueMensajes queueMsg) {
        this.socket = socket;
        this.user = user;
        this.queueMsg = queueMsg;
    }

    @Override
    public void run() {
        int intentos = 0;
        while (intentos < 3) {
            try {
                in = new ObjectInputStream(socket.getInputStream());
                String x = (String) in.readObject();
                queueMsg.addMensaje(user + ": " +x);
            } catch (Exception e) {
                intentos++;
                if (intentos < 3) {
                    System.out.println("> Intentando reconectar al cliente " + user + "...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.out.println("> ! El cliente " + user + " se ha desconectado.");
                    }
                } else {
                    System.out.println("Error 0202: El cliente " + user + " se ha desconectado.");
                    break;
                }
            }
        }
    }


    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }

}

