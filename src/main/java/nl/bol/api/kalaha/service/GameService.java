package nl.bol.api.kalaha.service;

import lombok.extern.slf4j.Slf4j;
import nl.bol.api.kalaha.model.GameBoard;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GameService {

    private final GameBoard gameBoard = GameBoard.builder().board(new int[14]).build();

    public GameBoard initNewGame() {

        for (int i = 0; i < 14; i++) {
            gameBoard.board[i] = 6;
        }
        gameBoard.board[0] = 0;
        gameBoard.board[7] = 0;

        return gameBoard;
    }

    public GameBoard player1Move(int userInput) {

        log.debug("Choose pit between 1 and 6");
        int boardValue = gameBoard.board[userInput];
        if (boardValue == 0) {
            log.debug("Choose pit between 1 and 6 again as position " + userInput + "have empty pits");
            return gameBoard;
        }
        int positionToMove = boardValue + userInput;
        gameBoard.board[userInput] = 0;
        for (int i = userInput + 1; i <= positionToMove; i++) {

            if (i % 14 != 0)
                gameBoard.board[i % 14]++;
            else
                positionToMove++;
        }
        if (positionToMove % 14 == 7) {
            log.debug("Extra turn for Player 1.. so Please enter no between 1 and 6");
            return gameBoard;
            //ToDo  Send Back the screen to Player 1 and force him to enter for extra turn and stop entering any input for Player 2
        }

        return gameBoard;
    }

    public GameBoard player2Move(int userInput) {
        log.debug("Choose pit between 8 and 13");
        int boardValue = gameBoard.board[userInput];
        gameBoard.board[userInput] = 0;
        if (boardValue == 0) {
            log.debug("Choose pit between 8 and 13 again as position " + userInput + "have empty pits");
            return gameBoard;
        }
        int positionToMove = boardValue + userInput;
        for (int i = userInput + 1; i <= positionToMove; i++) {
            if (i % 14 != 7)
                gameBoard.board[i % 14]++;
            else
                positionToMove++;
        }
        if (positionToMove % 14 == 0) {
            log.debug("Extra turn for Player 2.. so Please enter no between 8 and 13");
            return gameBoard;
            //ToDo  Send Back the screen to Player 2 and force him to enter for extra turn and stop entering any input for Player 1
        }

        return gameBoard;
    }

}
