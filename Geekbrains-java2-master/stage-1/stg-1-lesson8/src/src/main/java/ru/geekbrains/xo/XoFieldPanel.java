package ru.geekbrains.xo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XoFieldPanel extends JPanel {

    private XoController controller;

    private static final int IN_FIELD_DOT_PADDING = 5;

    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    private int fieldSize;
    private int fieldWidth;
    private int fieldHeight;
    private int cellWidth;
    private int cellHeight;

    public XoFieldPanel() {
        this.controller = new XoController(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.printf("Mouse button %s was clicked at X=%d, Y=%d%n", e.getButton(), e.getX(), e.getY());
                update(e);
            }
        });
    }

    public void startGame(int gameMode, int fieldSize, int winLength) {
        this.fieldWidth = getWidth();
        this.fieldHeight = getHeight();
        this.cellWidth = this.fieldWidth / fieldSize;
        this.cellHeight = this.fieldHeight / fieldSize;
        this.fieldSize = fieldSize;
        controller.initialize(gameMode, fieldSize, winLength);
        repaint();
    }

    private void update(MouseEvent e) {
        if (!controller.isGameActive()) return;
        if (controller.isGameOver()) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        controller.processMove(cellX, cellY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!controller.isGameActive()) return;
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, this.fieldHeight);

            int y = i * cellHeight;
            g.drawLine(0, y, this.fieldWidth, y);
        }

        int[][] field = controller.getField();

        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                if (controller.isEmptyCell(x, y)) continue;

                if (field[y][x] == XoConstants.DOT_O) {
                    g.setColor(new Color(1, 1, 255));
                    g.drawOval(x * cellWidth + IN_FIELD_DOT_PADDING,
                            y * cellHeight + IN_FIELD_DOT_PADDING,
                            cellWidth - IN_FIELD_DOT_PADDING * 2,
                            cellHeight - IN_FIELD_DOT_PADDING * 2);
                } else if (field[y][x] == XoConstants.DOT_X) {
                    g.setColor(Color.RED);
                    /* Отрисовка линии \ */
                    g.drawLine(x * cellWidth + IN_FIELD_DOT_PADDING,
                            y * cellHeight + IN_FIELD_DOT_PADDING,
                            (x + 1) * cellWidth - IN_FIELD_DOT_PADDING,
                            (y + 1) * cellHeight - IN_FIELD_DOT_PADDING);
                    /* Отрисовка линии / */
                    g.drawLine((x + 1) * cellWidth - IN_FIELD_DOT_PADDING,
                            y * cellHeight + IN_FIELD_DOT_PADDING,
                            x * cellWidth + IN_FIELD_DOT_PADDING,
                            (y + 1) * cellHeight - IN_FIELD_DOT_PADDING);
                } else {
                    throw new RuntimeException(
                            String.format("Can't recognize cell field[%d][%d]: %d", y, x, field[y][x]));
                }
            }
        }
        if (controller.isGameOver()) {
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (controller.getStateGameOver()) {
            case XoConstants.STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case XoConstants.STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
                break;
            case XoConstants.STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected gameOver state: " + controller.getStateGameOver());
        }
    }
}
