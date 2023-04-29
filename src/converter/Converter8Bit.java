package converter;

import spring.SpringArray;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Converter8Bit extends Converter {

    private static final int BITS = 8;

    public Converter8Bit() {
        super(BITS);
    }

    @Override
    protected String convertBitsToSprings(boolean[] bits) {
        StringBuilder sb = new StringBuilder();
        int numberOfBrackets = 0;
        for (boolean b : bits) {
            if (b) {
                sb.append("(");
                numberOfBrackets++;
            } else {
                if (numberOfBrackets > 0) {
                    sb.append(")");
                    numberOfBrackets--;
                }
            }
        }
        while (numberOfBrackets > 0) {
            sb.append(")");
            numberOfBrackets    --;
        }
        return sb.toString();
    }

    @Override
    public double evaluateDecimalValue(double[] amplifications, double[] frequencies) {
        int maxIndex = IntStream.range(0, amplifications.length)
                .reduce((i, j) -> amplifications[i] > amplifications[j] ? i : j)
                .orElse(0);

        return (frequencies[maxIndex] * 2) * Math.pow(Math.PI, 2);
    }
}
