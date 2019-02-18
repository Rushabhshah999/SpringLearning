package nl.bol.api.kalaha.validator;

import nl.bol.api.kalaha.model.Player;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PlayerNumberValidator {

    public boolean validatePlayer1(Player player, Errors errors) {

        if (!(player.getInputNumber() >= 1 && player.getInputNumber() < 7)) {
            errors.reject("Player 1 Input Number should be greater than 1 and less than 7");
            return true;
        } else if (StringUtils.isEmpty(player.getName())) {
            errors.reject("Player name must not be null");
            return true;
        }
        return false;
    }

    public boolean validatePlayer2(Player player, Errors errors) {

        if (!(player.getInputNumber() >= 8 && player.getInputNumber() < 14)) {
            errors.reject("Player 2 Input Number should not be greater than 13");
            return true;
        } else if (StringUtils.isEmpty(player.getName())) {
            errors.reject("Player name must not be null");
            return true;
        }
        return false;
    }
}
