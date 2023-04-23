import static java.lang.Math.sin;

public class Spring {
    private double stiffness;

    public Spring() {
        stiffness = 1d;
    }
    public Spring(double stiffness) {
        this.stiffness = stiffness;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        double[] x = new double[(int) Math.ceil(t / dt) + 1];
        double omega = Math.sqrt(stiffness);
        double ampl = Math.sqrt(x0 * x0 + (v0 / omega) * (v0 / omega));
        x[0] = x0;
        double velocity = v0;
        for (int i = 1; i < x.length; i++) {
            double time = i*dt;
            double phi = Math.atan2(x0, velocity / omega);
            x[i] = ampl * sin(omega * time + phi);
            velocity += (x[i] - x[i-1])/dt;
        }
        return x;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        double[] x = new double[(int) Math.ceil((t1-t0) / dt) + 1];
        double omega = Math.sqrt(stiffness);
        double ampl = Math.sqrt(x0 * x0 + (v0 / omega) * (v0 / omega));
        x[0] = x0;
        double velocity = v0;
        for (int i = 1; i < x.length; i++) {
            double phi = Math.atan2(x0, velocity / omega);
            double time = t0 + i*dt;
            x[i] = ampl * sin(omega * time + phi);
            velocity += (x[i] - x[i-1])/dt;
        }
        return x;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        double[] x = new double[(int) Math.ceil((t1-t0) / dt) + 1];
        double omega = Math.sqrt(stiffness/m);
        double ampl = Math.sqrt(x0 * x0 + (v0 / omega) * (v0 / omega));
        x[0] = x0;
        double velocity = v0;
        for (int i = 1; i < x.length; i++) {
            double time = t0 + i*dt;
            double phi = Math.atan2(x0, velocity / omega);
            x[i] = ampl * sin(omega * time + phi);
            velocity += stiffness/m* sin(omega*time)*dt;
        }
        return x;
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 1.0);
    }


    public double getStiffness() {
        return stiffness;
    }

    private void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }
}
