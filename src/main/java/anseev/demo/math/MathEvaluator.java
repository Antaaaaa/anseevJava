package anseev.demo.math;

import anseev.demo.enums.MathType;
import anseev.demo.model.MathResults;
import anseev.demo.service.MathResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static java.lang.Math.abs;


/**
    * Уравнение.
    *
    * @author Andrey Ansseev 14.10.2020
    * @version 1.0.0
**/
@Component
@RequiredArgsConstructor
public class MathEvaluator {
    /**
     * Service для маніпуляції даними в бд
     */
    private final MathResultsService mathResultsService;

    /**
     * Повертає значення обчислення
     *
     * Метод дихотомії  — метод, який описує алгоритм знаходження корнів функції
     *
     * @param a ліва границя відрізка.
     * @param b права границя відрізка.
     * @param e точність.
     * @return значення обчислення.
     * @see #f(double)
     **/
    public Double findDihotomiaWay(float a, float b, float eps) {
        float x = 0, c;
        while (abs(a - b) > eps) {
            c = (a + b) / 2;
            if (f(a) * f(c) <= 0) b = c;
            else {
                a = c;
                x = (a + b) / 2;
            }
        }
        return f(x);
    }
     /**
      * Повертає значення функції f(x) із рівняння f(x)=0.
      * @param x значення аргумента.
      * @return значення функції.
    **/
    private static double f(float x) {
        return Math.cos(x) - Math.sqrt(x);
    }

    /**
     * Повертає значення обчислення
     *
     * Метод хорд  — метод, який описує алгоритм знаходження корнів функції
     *
     * @param a ліва границя відрізка.
     * @param b права границя відрізка.
     * @param e точність.
     * @return значення обчислення.
     * @see #f(double)
     **/
    public double findMethodChord(double param1, double param2, double param3, double xPres, double xCurr, double e) {
        double xNext = 0;
        double tmp;
        do {
            tmp = xNext;
            xNext = xCurr - f(param1, param2, param3, xCurr) * (xPres - xCurr) /
                    (f(param1, param2, param3, xPres) - f(param1, param2, param3, xCurr));
            xPres = xCurr;
            xCurr = tmp;
        } while (Math.abs(xNext - xCurr) > e);
        return xNext;
    }
    /**
     * Повертає значення функції f(x) із рівняння f(x)=0.
     * @param param1 значення параметра #1 (x^(param1) + x*param2 + param3)
     * @param param2 значення параметра #2 (x^(param1) + x*param2 + param3)
     * @param param2 значення параметра #3 (x^(param1) + x*param2 + param3)
     * @param x значення аргумента.
     * @return значення функції.
     **/
    private static double f(double param1, double param2, double param3, double x) {
        return Math.pow(x, param1) - param2 * x - param3;
    }
    /**
     * Повертає значення обчислення
     *
     * Метод ньютона
     *
     * @param param1 - параметр рівняння
     * @return значення обчислення.
     * @see #f(double)
     **/
    public double findNewtonMethod(double param1) {
        double tolerance = .000000001;
        int max_count = 200;
        double x = param1;
        for (int count = 1; (Math.abs(fNewton(x)) > tolerance) && (count < max_count); count++) {
            x = x - fNewton(x) / fPrimeNewton(x);
//            System.out.println("Step: " + count + " x:" + x + " Value:" + fNewton(x));
        }
        if (Math.abs(fNewton(x)) <= tolerance) {
            return x;
        } else {
            return -1;
        }
    }
    /**
     * Повертає значення функції Math.sin(x)
     * @param x значення аргумента.
     * @return значення функції.
     **/
    private static double fNewton(double x) {
        return Math.sin(x);
    }
    /**
     * Повертає значення функції Math.cos(x)
     * @param x значення аргумента.
     * @return значення функції.
     **/
    private static double fPrimeNewton(double x) {
        return Math.cos(x);
    }


    @EventListener(ApplicationReadyEvent.class)
    private void startProgram() {
        mathResultsService.save(new MathResults(null, MathType.DIHITOMIA, findDihotomiaWay(0, 1, .00001f)));
        mathResultsService.save(new MathResults(null, MathType.CHORD, findMethodChord(3, 18, 83, 2, 10, .0001f)));
        mathResultsService.save(new MathResults(null,MathType.NEWTON, findNewtonMethod(1)));
        for (MathResults mathResults : mathResultsService.getAll()) {
            System.out.println(mathResults.getMathType() + " : " + mathResults.getResult());
        }
    }
}
