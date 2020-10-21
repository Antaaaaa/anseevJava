package anseev.demo.laba3.service;

import anseev.demo.laba3.exception.CalculationException;
import org.springframework.stereotype.Component;

@Component
public class MathServiceImplementation implements MathService {
    @Override
    public double[] calculate(double a, double b, double c) {
        double[] result = new double[2];
        double d = (b * b) - (4 * a * c);
        if (d < 0) throw new CalculationException("Desciminant < 0");
        else if (d == 0) {
            result[0] = result[1] = (-b) / (2 * a);
        } else {
            result[0] = (-b + Math.sqrt(d)) / (2 * a);
            result[1] = (-b - Math.sqrt(d)) / (2 * a);
        }
        return result;
    }
}
