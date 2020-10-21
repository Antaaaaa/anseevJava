package anseev.demo.laba3.service;

import org.springframework.stereotype.Service;

@Service
public interface MathService {
    double[] calculate(double a, double b, double c);
}
