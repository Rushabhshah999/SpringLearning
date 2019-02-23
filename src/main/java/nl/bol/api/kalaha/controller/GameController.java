package nl.bol.api.kalaha.controller;

import nl.bol.api.kalaha.exception.ApiError;
import nl.bol.api.kalaha.model.GameBoard;
import nl.bol.api.kalaha.model.Player;
import nl.bol.api.kalaha.service.GameService;
import nl.bol.api.kalaha.validator.PlayerNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;


@RestController
@EnableSwagger2
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerNumberValidator playerNumberValidator;

    @RequestMapping(path = "/kalaha/newGame", method = RequestMethod.GET, produces = "application/json")
    public GameBoard newGame() {
        return gameService.initNewGame();
    }

    @RequestMapping(path = "/kalaha/player1", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> player1Move(@Valid @RequestBody Player player, Errors errors) {

        //TODO check for Game win if all the pits of Player 1 is 0 then Player 2 win the Game to check this
        if (playerNumberValidator.validatePlayer1(player, errors)) {

            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(errors.getAllErrors().toString())
                    .errors(null)
                    .build();

            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
        GameBoard gameBoard = gameService.player1Move(player.getInputNumber());
        return new ResponseEntity<>(gameBoard, HttpStatus.OK);
    }

    @RequestMapping(path = "/kalaha/player2", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> player2Move(@Valid @RequestBody Player player, Errors errors) {

        //TODO check for Game win if all the pits of Player 2 is 0 then Player 1 win the Game
        if (playerNumberValidator.validatePlayer2(player, errors)) {


            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(errors.getAllErrors().toString())
                    .errors(null)
                    .build();

            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
        GameBoard gameBoard = gameService.player2Move(player.getInputNumber());
        return new ResponseEntity<>(gameBoard, HttpStatus.OK);
    }
}
