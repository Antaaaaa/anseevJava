package anseev.demo.laba3.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({CalculationException.class})
    public String calculationError(Model model){
        model.addAttribute("error", "Дискримінант < 0");
        return "laba3";
    }
}
