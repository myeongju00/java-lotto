package lotto.utils;

import java.util.List;
import java.util.regex.Pattern;
import lotto.domain.ErrorMessage;

public class Validator {
    private static final String INPUT_NUMBER_REGEX = "^[0-9]*$";
    public void isValidMoney(String input) {
        if(!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_IS_NOT_NUMBER.toString());
        }
        if(isZero(input)) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_IS_ZERO.toString());
        }
        if(isEnteredSpace(input)){
            throw new IllegalArgumentException(ErrorMessage.MONEY_IS_NOT_ENTERED.toString());
        }
        if(!is1000Units(Integer.parseInt(input))) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_IS_NOT_1000_UNIT.toString());
        }
    }

    public void isValidLottoNumbers(List<Integer> lottoNumbers) {
        if(!isLottoRange(lottoNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_IS_NOT_VALID_RANGE.toString());
        }
        if(!isSixNumbers(lottoNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_IS_NOT_SIX_NUMBERS.toString());
        }
    }

    private boolean isLottoRange(List<Integer> lottoNumbers) {
        int rightNumberCount = (int) lottoNumbers.stream()
                .filter(number -> 1 <= number && number <= 45).count();
        return rightNumberCount == 6;
    }

    private boolean isSixNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.size() == 6;
    }

    private boolean isNumber(String input) {
        Pattern numberPattern = Pattern.compile(INPUT_NUMBER_REGEX);
        return numberPattern.matcher(input).matches();
    }

    private boolean isZero(String input) {
        return input.equals("0");
    }

    private boolean isEnteredSpace(String input) {
        return input.equals("");
    }

    private boolean is1000Units(int money) {
        return money % 1000 == 0;
    }
}
