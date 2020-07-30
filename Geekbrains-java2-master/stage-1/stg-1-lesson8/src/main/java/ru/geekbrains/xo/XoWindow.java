package ru.geekbrains.xo;

import ru.geekbrains.xo.enums.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XoWindow extends JFrame {

    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_POS_X = 650;
    private static final int WINDOW_POS_Y = 250;
    private XoSettingsWindow settingsWindow;
    private XoFieldPanel fieldPanel;

    public XoWindow() throws InterruptedException {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Крестики - Нолики");
        setResizable(false);

        this.settingsWindow = new XoSettingsWindow(this);
        JButton buttonStart = createStartButton();
        JButton buttonExit = createExitButton();
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonExit);
        add(buttonPanel, BorderLayout.SOUTH);

        this.fieldPanel = new XoFieldPanel();
        add(fieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void startGame(GameMode gameMode, int fieldSize, int winLength) {
        this.fieldPanel.startGame(gameMode, fieldSize, winLength);
    }

    private JButton createStartButton() {
        JButton button = new JButton("Начать игру");

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        };
        button.addActionListener(listener);
        return button;
    }


    private JButton createExitButton() {
        JButton button = new JButton("<html><body><b>Выйти</b></body></html>");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return button;
    }


}
