package anseev.demo.laba3;

import anseev.demo.laba3.service.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

import static anseev.demo.laba3.MathController.MATH_CONTROLLER;

@Controller
@RequestMapping(MATH_CONTROLLER)
@RequiredArgsConstructor
public class MathController {
    static final String MATH_CONTROLLER = "/laba3";

    private final MathService mathService;

    @GetMapping
    public String index(@RequestParam(required = false) Double a, @RequestParam(required = false) Double b,
                        @RequestParam(required = false) Double c, Model model) {
        if (a != null && b != null && c != null) {
            model.addAttribute("result", Arrays.toString(mathService.calculate(a,b,c)));
        }
        return "laba3";
    }

}
