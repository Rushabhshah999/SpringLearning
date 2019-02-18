package nl.bol.api.kalaha.service;

import nl.bol.api.kalaha.model.GameBoard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void GivenPlayerWhenStartGameThenReturnNewGrid() {

        GameBoard gameBoard = gameService.initNewGame();
        assertEquals(gameBoard.board[0], 0);
        assertEquals(gameBoard.board[7], 0);
    }

    @Test
    public void GivenPlayer1WhenInputValidNumberButNoPitThenReturnGrid() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard.board[5] = 0;
        gameBoard = gameService.player1Move(5);
        assertEquals(gameBoard.board[0], 0);
        assertEquals(gameBoard.board[7], 0);
        assertEquals(gameBoard.board[5], 0);
    }

    @Test
    public void GivenPlayer1WhenInputValidNumberButPitThenReturnGrid() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard = gameService.player1Move(5);
        assertEquals(gameBoard.board[0], 0);
        assertEquals(gameBoard.board[5], 0);
        assertEquals(gameBoard.board[7], 1);
        assertEquals(gameBoard.board[11], 7);
        assertEquals(gameBoard.board[13], 6);
    }

    @Test
    public void GivenPlayer1WhenInputValidNumberPitThenReturnGridForExtraTurn() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard.board[1] = 6;
        gameBoard = gameService.player1Move(1);
        assertEquals(gameBoard.board[0], 0);
        assertEquals(gameBoard.board[1], 0);
        assertEquals(gameBoard.board[2], 7);
        assertEquals(gameBoard.board[7], 1);
        assertEquals(gameBoard.board[11], 6);
        assertEquals(gameBoard.board[13], 6);
    }

    @Test
    public void GivenPlayer1WhenInputValidNumberPitNoPlaceInPlayer2ThenReturnGridForExtraTurn() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard.board[1] = 14;
        gameBoard = gameService.player1Move(1);
        assertEquals(gameBoard.board[0], 0);
        assertEquals(gameBoard.board[1], 1);
        assertEquals(gameBoard.board[2], 8);
        assertEquals(gameBoard.board[7], 1);
        assertEquals(gameBoard.board[11], 7);
        assertEquals(gameBoard.board[13], 7);
    }

    @Test
    public void GivenPlayer21WhenInputValidNumberButNoPitThenReturnGrid() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard.board[9] = 0;
        gameBoard = gameService.player2Move(9);
        assertEquals(gameBoard.board[0], 0);
        assertEquals(gameBoard.board[7], 0);
        assertEquals(gameBoard.board[5], 6);
        assertEquals(gameBoard.board[9], 0);
    }

    @Test
    public void GivenPlayer2WhenInputValidNumberButPitThenReturnGrid() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard = gameService.player2Move(9);
        assertEquals(gameBoard.board[0], 1);
        assertEquals(gameBoard.board[1], 7);
        assertEquals(gameBoard.board[9], 0);
        assertEquals(gameBoard.board[11], 7);
        assertEquals(gameBoard.board[13], 7);
    }

    @Test
    public void GivenPlayer2WhenInputValidNumberButPitThenReturnGridForExtraTurn() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard.board[8] = 6;
        gameBoard = gameService.player2Move(8);
        assertEquals(gameBoard.board[0], 1);
        assertEquals(gameBoard.board[1], 6);
        assertEquals(gameBoard.board[2], 6);
        assertEquals(gameBoard.board[7], 0);
        assertEquals(gameBoard.board[11], 7);
        assertEquals(gameBoard.board[13], 7);
    }

    @Test
    public void GivenPlayer2WhenInputValidNumberPitNoPlaceInPlayer1ThenReturnGridForExtraTurn() {

        GameBoard gameBoard = gameService.initNewGame();
        gameBoard.board[8] = 14;
        gameBoard = gameService.player2Move(8);
        assertEquals(gameBoard.board[0], 1);
        assertEquals(gameBoard.board[1], 7);
        assertEquals(gameBoard.board[2], 7);
        assertEquals(gameBoard.board[7], 0);
        assertEquals(gameBoard.board[8], 1);
        assertEquals(gameBoard.board[11], 7);
        assertEquals(gameBoard.board[13], 7);
    }
}
