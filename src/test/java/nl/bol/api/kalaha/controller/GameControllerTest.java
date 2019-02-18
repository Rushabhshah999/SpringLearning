package nl.bol.api.kalaha.controller;

import nl.bol.api.kalaha.model.GameBoard;
import nl.bol.api.kalaha.model.Player;
import nl.bol.api.kalaha.service.GameService;
import nl.bol.api.kalaha.validator.PlayerNumberValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Mock
    private Errors errors;

    @Mock
    private PlayerNumberValidator playerNumberValidator;


    @InjectMocks
    private GameController gameController;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void GivenGameWhenInitNewGameThenReturnNewGameBoard() {
        when(gameService.initNewGame()).thenReturn(GameBoard.builder().board(new int[14]).build());
        GameBoard gameBoard = gameController.newGame();
        assertNotNull(gameBoard);
        assertNotNull(gameBoard.board);
        assertEquals(gameBoard.board.length, 14);
        verify(gameService).initNewGame();
    }

    @Test
    public void GivenGameWhenPlayerOnePlayThenReturnGameBoard() {

        int[] board = new int[]{6, 6, 6, 6, 0, 7, 1, 7, 7, 7, 6, 6, 6, 0};
        when(gameService.player1Move(5)).thenReturn(GameBoard.builder().board(board).build());
        Player player = Player.builder().name("shah").inputNumber(5).build();
        ResponseEntity responseEntity = gameController.player1Move(player, errors);
        GameBoard gameBoard = (GameBoard) responseEntity.getBody();
        assertNotNull(gameBoard);
        assertEquals(gameBoard.board, board);
        verify(gameService).player1Move(5);
    }

    @Test
    public void GivenGameWhenPlayerOnePlayInvalidInputThenReturnGameBoard() {

        Player player = Player.builder().name("shah").inputNumber(8).build();
        when(playerNumberValidator.validatePlayer1(player, errors)).thenReturn(Boolean.TRUE);
        ObjectError objectError = new ObjectError("Player", "Value is null");
        when(errors.getAllErrors()).thenReturn(Arrays.asList(objectError));
        ResponseEntity responseEntity = gameController.player1Move(player, errors);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(responseEntity);
    }

    @Test
    public void GivenGameWhenPlayerTwoPlayThenReturnGameBoard() {

        int[] board = new int[]{6, 6, 6, 6, 6, 6, 0, 0, 7, 7, 7, 7, 7, 7};
        when(gameService.player2Move(8)).thenReturn(GameBoard.builder().board(board).build());
        Player player = Player.builder().name("vora").inputNumber(8).build();
        ResponseEntity responseEntity = gameController.player2Move(player, errors);
        GameBoard gameBoard = (GameBoard) responseEntity.getBody();
        assertNotNull(gameBoard);
        assertEquals(gameBoard.board, board);
        verify(gameService).player2Move(8);
    }

    @Test
    public void GivenGameWhenPlayerTwoPlayInvalidInputThenReturnGameBoard() {

        Player player = Player.builder().name("shah").inputNumber(5).build();
        when(playerNumberValidator.validatePlayer2(player, errors)).thenReturn(Boolean.TRUE);
        ObjectError objectError = new ObjectError("Player", "Value is null");
        when(errors.getAllErrors()).thenReturn(Arrays.asList(objectError));
        ResponseEntity responseEntity = gameController.player2Move(player, errors);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(responseEntity);
    }

}
