package com.github.alexeses.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlEnviar extends JPanel{
    private JTextField txtTexto;
    private JButton btnSend;
    private ActionListenerSendMsg listener;

    public void setListener(ActionListenerSendMsg listener) {
        this.listener = listener;
    }
    
    public PnlEnviar(){
        txtTexto = new JTextField(20);
        btnSend = new JButton("Enviar");
        
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                listener.enviarButtonClick();
            }
        });
        
        this.add(txtTexto);
        this.add(btnSend);
    }
    
    public void clean(){
        this.txtTexto.setText("");
    }

    public String getMensaje(){
        return txtTexto.getText();
    }
}
