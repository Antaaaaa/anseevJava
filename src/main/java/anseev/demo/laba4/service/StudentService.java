package anseev.demo.laba4.service;

import anseev.demo.laba4.model.Student;
import anseev.demo.laba4.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsWhereAvgMoreThan60() {
        return studentRepository.findAll()
                .stream()
                .filter(i -> ((i.getRateOne() + i.getRateTwo()) / 2) > 60)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Double getConditionPercent() {
        return BigDecimal.valueOf((double) getStudentsWhereAvgMoreThan60().size() / getAllStudent().size())
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @PostConstruct
    public void initStudents() {
        // Good conditions
        studentRepository.save(new Student(null, "Женя", 65L, 70L));
        studentRepository.save(new Student(null, "Макс", 80L, 70L));
        studentRepository.save(new Student(null, "Юрій", 65L, 90L));
        studentRepository.save(new Student(null, "Валя", 100L, 70L));
        studentRepository.save(new Student(null, "Генадій", 95L, 80L));
        studentRepository.save(new Student(null, "Андрій", 72L, 66L));
        studentRepository.save(new Student(null, "Владислав", 82L, 90L));
        studentRepository.save(new Student(null, "Гліб", 85L, 92L));

        // Wrong conditions
        studentRepository.save(new Student(null, "Владислав", 45L, 50L));
        studentRepository.save(new Student(null, "Гліб", 10L, 15L));
    }
}
