package ru.geekbrains.gui;

import ru.geekbrains.core.ChatDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientSettingsWindow extends JFrame {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 100;

    private ClientGUI clientGUI;

    private JTextField nickNameField = new JTextField();
    private JButton buttonSaveSettings = new JButton("Завершить настройки");

    public ClientSettingsWindow(ClientGUI clientGUI){
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.clientGUI = clientGUI;
        setResizable(false);
        setTitle("Настройки: " + clientGUI.getTitle());
        setLocationRelativeTo(clientGUI);
        setLayout(new GridLayout(2, 1));

        nickNameField.setText(clientGUI.getNickname());
        add(nickNameField);

        buttonSaveSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSettings();
            }
        });

        add(buttonSaveSettings);
        setVisible(true);
    }

    private void setSettings (){
        String nickName = nickNameField.getText();
        if (!nickName.equals(clientGUI.getNickname())) {
            clientGUI.setNickname(nickName);
            try {
                ChatDB.updateNickname(clientGUI.getLogin(),nickName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        setVisible(false);
    }
}
