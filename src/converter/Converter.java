package converter;

import spring.Spring;
import transform.FT;


public abstract class Converter {
    protected int numBits;

    public Converter(int numBits) {
        this.numBits = numBits;
    }


    protected abstract String convertBitsToSprings(boolean[] bits);

    public abstract double evaluateDecimalValue(double[] amplifications, double[] frequencies);

    public double[] computeOscillations(Spring spring, double startTime, double endTime, double dt) {
        return spring.move(startTime, endTime, dt, 0, 1, 2);
    }

    public double[] calculateFrequencyAmplitudes(Spring spring, double startTime, double endTime, double dt) {
        final double[] oscillations = computeOscillations(spring, startTime, endTime, dt);
        final FT ft = new FT(oscillations);
        return ft.amplitude();
    }
}
