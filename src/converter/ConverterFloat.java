package converter;

public class ConverterFloat extends Converter {

    private static final int BITS = 32;

    public ConverterFloat() {
        super(BITS);
    }

    @Override
    protected String convertBitsToSprings(boolean[] bits) {
        return null;
    }

    @Override
    public double evaluateDecimalValue(double[] amplifications, double[] frequencies) {
        return 0;
    }
}
