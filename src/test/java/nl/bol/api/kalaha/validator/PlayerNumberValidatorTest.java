package nl.bol.api.kalaha.validator;

import nl.bol.api.kalaha.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class PlayerNumberValidatorTest {

    @InjectMocks
    private PlayerNumberValidator playerNumberValidator;

    @Mock
    private Errors errors;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void GivenPlayer1WhenInputInvalidNumberThenReturnFalse() {

        Player player = Player.builder().name("rushabh").inputNumber(8).build();
        errors.reject("Player 1 Input Number should be greater than 1 and less than 7");
        assertTrue(playerNumberValidator.validatePlayer1(player, errors));
    }

    @Test
    public void GivenPlayer1WhenInputInvalidNameThenReturnFalse() {

        Player player = Player.builder().name("").inputNumber(5).build();
        errors.reject("Player name must not be null");
        assertTrue(playerNumberValidator.validatePlayer1(player, errors));
    }

    @Test
    public void GivenPlayer1WhenInputValidNameThenReturnTrue() {

        Player player = Player.builder().name("shah").inputNumber(5).build();
        assertFalse(playerNumberValidator.validatePlayer1(player, errors));
    }

    @Test
    public void GivenPlayer2WhenInputInvalidNumberThenReturnFalse() {

        Player player = Player.builder().name("").inputNumber(6).build();
        errors.reject("Player 2 Input Number should not be greater than 13");
        assertTrue(playerNumberValidator.validatePlayer2(player, errors));
    }

    @Test
    public void GivenPlayer2WhenInputInvalidNameThenReturnFalse() {

        Player player = Player.builder().name("").inputNumber(9).build();
        errors.reject(" Player name must not be null");
        assertTrue(playerNumberValidator.validatePlayer2(player, errors));
    }

    @Test
    public void GivenPlayer2WhenInputValidNameThenReturnTrue() {

        Player player = Player.builder().name("shah").inputNumber(9).build();
        assertFalse(playerNumberValidator.validatePlayer2(player, errors));
    }
}
