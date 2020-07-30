package ru.geekbrains.xo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XoSettingsWindow extends JFrame {

    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 270;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Размер поля: ";
    private static final String WIN_LENGTH_PREFIX = "Длина победы в символах: ";

    private XoWindow xoWindow;
    private JRadioButton humVSAI;
    private JRadioButton humVShum;
    private JSlider slideWinLen;
    private JSlider slideFieldSize;

    public XoSettingsWindow(XoWindow xoWindow) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.xoWindow = xoWindow;
        Rectangle gameWindowBounds = xoWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        setResizable(false);
        setTitle("Создать игру");
        setLayout(new GridLayout(8, 1));
        addGameModeButtons();
        addGameControls();

        JButton btnStart = new JButton("Создать новую игру");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classStartGame();
            }
        });
        add(btnStart);
    }

    private void addGameModeButtons() {
        JLabel label = new JLabel(" Выберите режим");
        add(label);
        humVSAI = new JRadioButton("Человек vs Компьютер");
        humVShum = new JRadioButton("Человек vs Человек");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(humVSAI);
        buttonGroup.add(humVShum);
        humVSAI.setSelected(true);
        add(humVSAI);
        add(humVShum);
    }

    private void addGameControls() {
        JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);

        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });

        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
        add(lbFieldSize);
        add(slideFieldSize);
        add(lbWinLength);
        add(slideWinLen);
    }

    private void classStartGame() {
        int gameMode;
        if(humVSAI.isSelected()) {
            gameMode = XoConstants.GAME_MODE_HVAI;
        } else if (humVShum.isSelected()) {
            gameMode = XoConstants.GAME_MODE_HVH;
        } else {
            throw new RuntimeException("Данный режим игры не поддерживается");
        }

        int fieldSize = slideFieldSize.getValue();
        int winLength = slideWinLen.getValue();
        xoWindow.startGame(gameMode, fieldSize, winLength);
        setVisible(false);
    }


}
