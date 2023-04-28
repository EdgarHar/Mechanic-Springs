import java.util.stream.IntStream;

public class FT {

    private final double[] real;
    private final double[] imaginary;
    private final int N;

    public FT(double[] in) {
        this.N = in.length;
        this.real = new double[N];
        this.imaginary = new double[N];
        for (int k = 0; k < N; k++) {
            for (int n = 0; n < N; n++) {
                double theta = (-2 * Math.PI * k * n) / N;
                real[k] += in[n] * Math.cos(theta);
                imaginary[k] += in[n] * Math.sin(theta);
            }
        }
    }

    public double[] amplitude() {
        final double[] amplitude = new double[N];
        IntStream.range(0, N)
                .forEach(k -> amplitude[k] = (Math.sqrt(Math.pow(k, 2) + Math.pow(imaginary[k], 2)) / N));

        return amplitude;
    }
}