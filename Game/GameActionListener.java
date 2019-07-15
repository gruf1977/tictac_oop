package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
private int row;
private int cell;
private GameButton button;

    GameActionListener(int row, int cell, GameButton gButton){
        this.row =row;
        this.cell = cell;
        this.button = gButton;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isTurnable(row, cell)) {
            updateByplaersData(board);
            if (board.isFull()) {
                board.getGame().showMessage("Ничья! Победила дружба.");
                board.emptyField();
            }
            else {
                updateByAIData(board);

            }
        } else {
            board.getGame().showMessage("Некорректный ход!");
        }



    }

    /*Ход человека
    @param board GameBoard - ссылка на игровое поле
     */
    private void updateByplaersData(GameBoard board){

        board.updateGameField(row, cell);
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
if(board.checkWin()){
    button.getBoard().getGame().showMessage("Поздравляем Вы выйграли");
    board.emptyField();
}

else {
    board.getGame().passTurn();
}

    }
    private void updateByAIData(GameBoard board){
// генерация координат хода комьютера
 int x, y;

 Random rnd = new Random();
 do {
     x = rnd.nextInt(GameBoard.dimension);
     y = rnd.nextInt(GameBoard.dimension);

 }while (!board.isTurnable(x, y));


//обновим матрицу поля
        board.updateGameField(x, y);


        //обновим символ кнопки
        int cellIndex = GameBoard.dimension*x + y;
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        if(board.checkWin()){
            button.getBoard().getGame().showMessage("Копьютер выйграл");
            board.emptyField();
        }
        else {
            //передать ход
            board.getGame().passTurn();
        }
    }
}

