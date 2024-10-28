package ca.uwaterloo.cs846;

public class Subject {

    public int foo(int x, int y) {
        int a = Math.abs(x);
        int b = Math.abs(y);
        if (a == b) {
            return a;
        } else {
            int c = a - b;
            while (a - b > 0) {
                a++;
                b--;
            }
            return a;
        }
    }

    public double bar(double t, double r) {
        return t * r;
    }

    public double bar(double t, int p) {
        return bar(t, (1 - p / 100.0d));
    }

    public double baz(double[] x, double[] y, int[] z) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            if (i < y.length) {
                sum += bar(x[i], y[i]);
            } else if (i < z.length) {
                sum += bar(x[i], z[i]);
            } else {
                sum += x[i];
            }
        }
        return sum;
    }
}
