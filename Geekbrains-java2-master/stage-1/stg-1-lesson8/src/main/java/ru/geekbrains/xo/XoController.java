package ru.geekbrains.xo;

import ru.geekbrains.xo.enums.Dots;
import ru.geekbrains.xo.enums.GameMode;

import static ru.geekbrains.xo.enums.Dots.DOT_O;

import java.util.Random;

public class XoController {

    private int[][] field;
    private int fieldSize;
    private int winLength;
    private GameMode gameMode;

    private int stateGameOver;
    private boolean isGameOver;
    private boolean gameActive = false;

    private int playerOneDot;
    private int playerTwoDot;

    private XoFieldPanel fieldPanel;

    private static final Random RANDOM = new Random();

    public XoController(XoFieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }

    public int[][] getField() {
        return field;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getStateGameOver() {
        return stateGameOver;
    }

    public void initialize(GameMode gameMode, int fieldSize, int winLength) {
        this.gameMode = gameMode;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
        this.gameActive = true;
        this.isGameOver = false;

        this.playerOneDot = XoConstants.DOT_X;
        this.playerTwoDot = XoConstants.DOT_O;

        this.field = new int[fieldSize][fieldSize];
    }

    private void setGameOver(int gameOverState) {
        isGameOver = true;
        stateGameOver = gameOverState;
    }

    public void processMove(int cellX, int cellY) {
        if (!isEmptyCell(cellX, cellY)) {
            return;
        }
        field[cellY][cellX] = playerOneDot;
        fieldPanel.repaint();
        if (isWinnerSequenceExistsFor(playerOneDot)) {
            setGameOver(XoConstants.STATE_WIN_HUMAN);
            return;
        }
        if (isDraw()) {
            setGameOver(XoConstants.STATE_DRAW);
            return;
        }

        aiTurn(playerTwoDot, playerOneDot);
        fieldPanel.repaint();
        if (isWinnerSequenceExistsFor(playerTwoDot)) {
            setGameOver(XoConstants.STATE_WIN_AI);
            return;
        }
        if (isDraw()) {
            setGameOver(XoConstants.STATE_DRAW);
        }
    }


    /* Ход компьютера */
    private void aiTurn(int dotAi, int dotHuman) {
        /* Проверка выигрыша компьютера на следующем ходу */
        if(turnAIWinCell(dotAi)) {
            return;
        }
        /* Проверка выигрыша опонента на следующем ходу */
        if(turnHumanWinCell(dotAi, dotHuman)) {
            return;
        }
        int x;
        int y;
        do {
            /* Ход в случайную клетку */
            x = RANDOM.nextInt(fieldSize);
            y = RANDOM.nextInt(fieldSize);
        } while (!isEmptyCell(x, y));
        field[y][x] = dotAi;
    }

    // Проверка, может ли выиграть комп
    private boolean turnAIWinCell(int dotAi) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (isEmptyCell(j, i)) {
                    /* Ставим 0 в каждую клетку линии */
                    field[i][j] = dotAi;
                    /* Если выиграл - возвращаем инстину */
                    if (isWinnerSequenceExistsFor(dotAi)) {
                        return true;
                    }
                    /* Если нет - возвращаем пустоту в клетку и идем дальше */
                    field[i][j] = XoConstants.DOT_EMPTY;
                }
            }
        }
        return false;
    }

    /* Проверка, выиграет ли игрок своим следующим ходом */
    private boolean turnHumanWinCell(int dotAi, int dotHuman) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (isEmptyCell(j, i)) {
                    /* Ставим X в каждую клетку линии */
                    field[i][j] = dotHuman;
                    /* Если игрок выигрывает - ставим 0 и возвращаем истину */
                    if (isWinnerSequenceExistsFor(dotHuman)) {
                        field[i][j] = dotAi;
                        return true;
                    }
                    /* Если нет - возвращаем пустоту в клетку и идем дальше */
                    field[i][j] = XoConstants.DOT_EMPTY;
                }
            }
        }
        return false;
    }

    /* Проверка на победу */
    private  boolean isWinnerSequenceExistsFor(int dot) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                /* Проверка горизонталей */
                if (checkLine(i, j, 1, 0, winLength, dot)) {
                    return true;
                }
                /* Проверка диагонали от точки направо вниз */
                if (checkLine(i, j, 1, 1, winLength, dot)) {
                    return true;
                }
                /* Проверка вертикали */
                if (checkLine(i, j, 0, 1, winLength, dot)) {
                    return true;
                }
                /* Проверка диагонали от точки направо вверх  */
                if (checkLine(i, j, 1, -1, winLength, dot)) {
                    return true;
                }
            }
        }
        return false;
    }

    // проверка линии
    private boolean checkLine(int x, int y, int offsetX, int offsetY, int winLength, int dot) {
        /* Считаем конеч проверяемой последовательности */
        final int xEnd = x + (winLength - 1) * offsetX;
        final int yEnd = y + (winLength - 1) * offsetY;
        /* Проверяем не выходил ли ячейка за границы */
        if (!isValidCell(xEnd, yEnd)) {
            return false;
        }
        /* Идем по всей линии */
        for (int i = 0; i < winLength; i++) {
            /* Проверяем соответвие символов. Если не соответствует, то возвращаем ложь - победы в данной последовательности нет */
            if (field[y + i * offsetY][x + i * offsetX] != dot) {
                return false;
            }
        }
        /* Если дошли сюда - все символы в последовательности соответствую искомому символу - возвращаем истину */
        return true;
    }

    /* Проверка ничьей */
    private boolean isDraw() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == XoConstants.DOT_EMPTY) return false;
            }
        }
        return true;
    }

    /* Корректная ли ячейка? */
    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSize && y >= 0 && y < fieldSize;
    }

    /* Пустая ли данная ячейка? */
    public boolean isEmptyCell(int x, int y) {
        return field[y][x] == XoConstants.DOT_EMPTY;
    }
}
