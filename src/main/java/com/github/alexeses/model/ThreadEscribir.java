package com.github.alexeses.model;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadEscribir extends Thread {

    private QueueMensajes queueMsg;
    private ArrayList<Cliente> users;

    public ThreadEscribir(QueueMensajes queue, ArrayList<Cliente> users) {
        this.queueMsg = queue;
        this.users = users;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadEscribir.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!queueMsg.isEmpty()) {
                try {
                    for (Cliente usuario : users) {
                        ObjectOutputStream out = new ObjectOutputStream(usuario.getSocket().getOutputStream());
                        out.writeObject(queueMsg.getMensaje());
                        out.flush();
                    }
                    queueMsg.popCola();
                } catch (Exception ignored) {
                }
            }
        }
    }
}