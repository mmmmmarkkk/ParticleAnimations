package org.mmmmarkkk.particleanimations;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@UtilityClass
@Log
public class NumUtils {

    public boolean isFavorable(int particleCount, int size) {
        double step = (double) size / Math.sqrt((double) particleCount / 6);

        double decimalPart = (step - (int) step);
        String decimalStr = String.valueOf(decimalPart);
        if (decimalStr.length() == 3 && decimalStr.charAt(2) == '5') {
            log.info("Данное число благоприятно для значения количества партиклов для куба. particleCount = " + particleCount);
            return true;
        } else if (decimalStr.length() == 2 && decimalStr.charAt(1) == '5') {
            log.info("Данное число благоприятно для значения количества партиклов для куба. particleCount = " + particleCount);
            return true;
        } else {
            return false;
        }
    }
}
