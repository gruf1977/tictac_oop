package Game;

import javax.swing.*;

public class Game {

    private GameBoard board; //игровое поле
    private GamePlayer[] gamePlayers = new GamePlayer[2]; // массив игроков
    private int playersTurns = 0; // индекс игрока


    public Game() {
     this.board = new GameBoard(this);




    }
public void initGame() {
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, 'O');


}

 /*Метод передачи хода*/
    void passTurn() {
        if (playersTurns==0)
            playersTurns = 1;
        else
            playersTurns =0;
    }
    /*Получение объекта текущего игрока
    * @return GamePlayer объект игрока
    * */
    GamePlayer getCurrentPlayer (){return gamePlayers[playersTurns];}
/*
* Метод показа popup-а для пользователя
* @param messageText - текст сообщения*/
void showMessage(String messageText){
    JOptionPane.showMessageDialog(board, messageText);

}


}

