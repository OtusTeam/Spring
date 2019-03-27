package ru.otus.example.HibernateFetchModeDemo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.example.HibernateFetchModeDemo.models.Mentor;
import ru.otus.example.HibernateFetchModeDemo.models.Student;
import ru.otus.example.HibernateFetchModeDemo.models.Teacher;
import ru.otus.example.HibernateFetchModeDemo.repositories.MentorRepository;
import ru.otus.example.HibernateFetchModeDemo.repositories.StudentRepository;
import ru.otus.example.HibernateFetchModeDemo.repositories.TeacherRepository;

import java.util.stream.Collectors;


@ShellComponent
public class ShellCommands {

    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;
    private final TeacherRepository teacherRepository;

    public ShellCommands(StudentRepository studentRepository, MentorRepository mentorRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
        this.teacherRepository = teacherRepository;
    }


    @ShellMethod(value = "Show all students", key = "find-all-students")
    public String findAllStudents(){
        System.out.println();
        return OFFSET + studentRepository.findAll().stream().map(Student::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Show all mentors", key = "find-all-mentors")
    public String findAllMentors(){
        System.out.println();
        return OFFSET + mentorRepository.findAll().stream().map(Mentor::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Show all teachers", key = "find-all-teachers")
    public String findAllTeachers(){
        System.out.println();
        return OFFSET + teacherRepository.findAll().stream().map(Teacher::toString).collect(Collectors.joining("\n"));
    }
}
