package com.github.alexeses.login;

import com.github.alexeses.gui.PrincipalFrame;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private Login l;
    private Botones b;

    public Main() {

        super("FastChat - Login");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(400, 150);
        super.setLocationRelativeTo(null);
        super.setResizable(Boolean.FALSE);

        l = new Login();
        b = new Botones();
        b.setListener(new BotonesListener() {
            @Override
            public void loginButtonClick() {
                new PrincipalFrame(l.getUser());
                Main.this.setVisible(Boolean.FALSE);
            }

            @Override
            public void cancelarButtonClick() {
                confirmExit();
            }
        });

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        super.setLayout(new BorderLayout());
        super.add(l, BorderLayout.CENTER);
        super.add(b, BorderLayout.SOUTH);
        super.setVisible(true);
    }

    private void confirmExit() {
        int confirm = JOptionPane.showOptionDialog(
                this, "Are You Sure to Close Application?",
                "Confirm exit", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
