package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {

    static int dimension = 3; //размерность поля
    static int cellSize = 150; //размер одной клетки
    private char[][] gameField; //матрица игры
    private GameButton[] gameButtons; //массив кнопок
    public char nullSymbol = '\u0000'; // null символ
    private Game game; // ссылка на игру


    public GameBoard(Game currentGame) {

        this.game = currentGame;
        initField();
    }

    /*
    Метод инициализации и отображения игрового поля
    * */
    private void initField() {
        //задаем основные настройки окна игры
        setBounds(cellSize * dimension, cellSize * dimension, 400, 400);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout()); // задаем поле игры

       // заполняем наше окно программы
       JPanel controlPanel = new JPanel(); // панель управления игрой
        JButton newGameButton = new JButton("Новая игра"); //кнопка новая игра

        newGameButton.addActionListener(new ActionListener() {  //навешиваем лисинер на кнопку "Новая игра"
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField(); // очистка игрового поля

            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * dimension, 150);


        JPanel gameFieldPanel = new JPanel(); //панель самой игры
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension)); //табличное размещение
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension); // размер таблицы игрового поля

        gameField = new char[dimension][dimension]; //инициализируем матрицу нашей игры состоящую из char -символов
        gameButtons = new GameButton[dimension * dimension]; //иницализируем кнопку [размер так как удобно так хранить см. презентацию]

//инициализируем игровое поле и заполняем кнопками
        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this); // создание нового экземпляра кнопки и передаем на текущее поле
            gameFieldPanel.add(fieldButton); //передаем массив кнопок игровому полю
            gameButtons[i] = fieldButton; // ссылка на кнопку

        }

        getContentPane().add(controlPanel, BorderLayout.NORTH); //добавляем панель
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER); //добавляем игровое поле
        System.out.println("Вызуализация окна");
        getContentPane().setVisible(true); // отобразить окно

    }

    /*очистка игрового поля*/
    public void emptyField() {
        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;

        }
    }

    Game getGame() {
        return game;
    }
/*Метод проверки доступности клетки для хода
@param x - по горизонтали
@param y - по вертикали
@return boolean
* */

    boolean isTurnable(int x, int y) {
        boolean result = false;
        if (gameField[y][x] == nullSymbol)
            result = true;
        return result;
    }

    void updateGameField(int x, int y) {
        gameField[x][y] = game.getCurrentPlayer().getPlayerSign();
    }

    boolean checkWin() {
        boolean result = false;
        char playerSimbol = getGame().getCurrentPlayer().getPlayerSign();
        if (chekWinDiagonals2(playerSimbol) || chekWinDiagonals1(playerSimbol) || chekWinLines(playerSimbol) || chekWinСolumn(playerSimbol)) {
            result = true;
        }
        return result;
    }

    /*Проверка выйгрыша по диагонали1*/
    private boolean chekWinDiagonals1(char playerSymbol) {
        boolean result = false;
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            if (gameField[i][i] == playerSymbol) {
                count++;
            }
        }
        if (count == dimension) {

            result = true;


        }


        return result;
    }

    /*Проверка выйгрыша по диагонали2*/
    private boolean chekWinDiagonals2(char playerSymbol) {
        boolean result = false;
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            if (gameField[dimension - i - 1][i] == playerSymbol) {
                count++;
            }
        }
        if (count == dimension) {

            result = true;


        }


        return result;
    }


    /*Проверка выйгрыша по линиям*/
    private boolean chekWinLines(char playerSymbol) {
        boolean result = false;
        int count = 0;

        for (int i = 0; i < dimension; i++) {
            count = 0;
            for (int j = 0; j < dimension; j++) {
                if (gameField[i][j] == playerSymbol) {
                    count++;
                }
            }
            if (count == dimension) {

                result = true;
                break;

            }
        }

        return result;
    }

    /*Проверяем выйгрыш по столбцам */
    private boolean chekWinСolumn(char playerSymbol) {

        boolean result = false;
        int count = 0;

        for (int i = 0; i < dimension; i++) {
            count = 0;
            for (int j = 0; j < dimension; j++) {
                if (gameField[j][i] == playerSymbol) {
                    count++;
                }
            }
            if (count == dimension) {

                result = true;
                break;

            }
        }

        return result;
    }


    /*Метод проверки заполнености поля*/
    boolean isFull() {
        boolean result = true;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (gameField[i][j] == nullSymbol)
                    result = false;

            }

        }
        return result;
    }
}