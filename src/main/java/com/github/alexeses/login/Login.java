package com.github.alexeses.login;

import javax.swing.*;
import java.awt.*;
public class Login extends JPanel {
    private JTextField txtUser;
    private JLabel lblUser;

    public Login(){
        lblUser = new JLabel("User name:");
        txtUser = new JTextField(15);
        this.add(lblUser);
        this.add(txtUser);


        //txtUser = new JTextField(15);
        //lblUser = new JLabel("Usuario");
        //
        //this.add(lblUser);
        //this.add(txtUser, BorderLayout.WEST);
    }
    
    public String getUser(){
        return txtUser.getText();
    }

}

