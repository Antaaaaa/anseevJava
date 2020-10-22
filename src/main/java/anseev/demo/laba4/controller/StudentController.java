package anseev.demo.laba4.controller;

import anseev.demo.laba4.model.Student;
import anseev.demo.laba4.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static anseev.demo.laba4.controller.StudentController.STUDENT_CONTROLLER;

@Controller
@RequiredArgsConstructor
@RequestMapping(STUDENT_CONTROLLER)
public class StudentController {

    static final String STUDENT_CONTROLLER = "/laba4";

    private final String STUDENT_PARAM = "students";
    private final String STUDENT_AVG_PARAM = "avg";
    private final StudentService studentService;

    @GetMapping
    public String index() {
        return "laba4Form";
    }

    @GetMapping(value = "/results")
    public String result(Model model, @RequestParam(required = false, defaultValue = "false") Boolean condition) {
        if (condition) {
            model.addAttribute(STUDENT_PARAM, studentService.getStudentsWhereAvgMoreThan60());
        } else {
            model.addAttribute(STUDENT_PARAM, studentService.getAllStudent());
        }
        model.addAttribute(STUDENT_AVG_PARAM, studentService.getConditionPercent());
        return "laba4Results";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("rateOne") Long rateOne,
                             @RequestParam("rateTwo") Long rateTwo) {
        studentService.addStudent(new Student(null, name, rateOne, rateTwo));
        return "redirect:/laba4/results";
    }
}
