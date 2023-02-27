package com.github.alexeses.model;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMensajes {

    public Queue<String> mensajes;

    public QueueMensajes() {
        this.mensajes = new LinkedList<>();
    }

    public String getMensaje() {
        if (!this.mensajes.isEmpty()) {
            return this.mensajes.peek();
        } else {
            return "";
        }
    }

    public void addMensaje(String mensaje) {
        this.mensajes.add(mensaje);
    }

    public boolean isEmpty() {
        return mensajes.isEmpty();
    }

    public void popCola() {
        mensajes.poll();
    }


}
