import transform.FT;

public abstract class Converter {

    protected abstract double[] convertBitsToSprings(boolean[] bits);

    public abstract double evaluateDecimalValue(boolean[] bits);

    public double[] computeOscillations(Spring spring, double startTime, double endTime, double dt) {
        return spring.move(startTime, endTime, dt, 0, 1, 2);
    }

    public double[] calculateFrequencyAmplitudes(Spring spring, double startTime, double endTime, double dt) {
        final double[] oscillations = computeOscillations(spring, startTime, endTime, dt);
        final FT ft = new FT(oscillations);
        return ft.amplitude();
    }
}
