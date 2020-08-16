package ru.geekbrains.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientSettingsWindow extends JFrame {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 100;

    private ChatClientGUI chatClientGUI;

    private JTextField nickNameField = new JTextField();
    private JButton buttonSaveSettings = new JButton("Завершить настройки");

    public ClientSettingsWindow(ChatClientGUI chatClientGUI){
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.chatClientGUI = chatClientGUI;
        setResizable(false);
        setTitle("Настройки: " + chatClientGUI.getTitle());
        setLocationRelativeTo(chatClientGUI);
        setLayout(new GridLayout(2, 1));

        nickNameField.setText(chatClientGUI.getNickname());
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
        if (!nickName.equals(chatClientGUI.getNickname())) {
            chatClientGUI.changeNickname(nickName);
        }

        setVisible(false);
    }
}
