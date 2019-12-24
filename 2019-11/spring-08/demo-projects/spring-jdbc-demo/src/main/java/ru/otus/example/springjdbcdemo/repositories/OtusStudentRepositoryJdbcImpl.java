package ru.otus.example.springjdbcdemo.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.example.springjdbcdemo.models.Course;
import ru.otus.example.springjdbcdemo.models.OtusStudent;
import ru.otus.example.springjdbcdemo.repositories.ext.OtusStudentResultSetExtractor;
import ru.otus.example.springjdbcdemo.repositories.ext.StudentCourseRelation;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OtusStudentRepositoryJdbcImpl implements OtusStudentRepositoryJdbc {

    private final CourseRepositoryJdbc courseRepository;
    private final JdbcOperations op;

    @Override
    public List<OtusStudent> findAllWithAllInfo() {
        List<Course> courses = courseRepository.findAllUsed();
        List<StudentCourseRelation> relations = getAllRelations();
        Map<Long, OtusStudent> students = op.query("select os.id, os.name, a.id avatar_id, a.photo_url, e.id email_id, e.email " +
                        "from (otus_students os left join avatars a on " +
                        "os.avatar_id = a.id) left join emails e on os.id = e.student_id",
                new OtusStudentResultSetExtractor());

        mergeStudentsInfo(students, courses, relations);
        return new ArrayList<>(Objects.requireNonNull(students).values());
    }

    private List<StudentCourseRelation> getAllRelations() {
        return op.query("select student_id, course_id from student_courses sc order by student_id, course_id",
                (rs, i) -> new StudentCourseRelation(rs.getLong(1), rs.getLong(2)));
    }

    private void mergeStudentsInfo(Map<Long, OtusStudent> students, List<Course> courses, List<StudentCourseRelation> relations) {
        Map<Long, Course> coursesMap = courses.stream().collect(Collectors.toMap(Course::getId, c -> c));
        relations.forEach(r -> {
            if (students.containsKey(r.getStudentId()) && coursesMap.containsKey(r.getCourseId())) {
                students.get(r.getStudentId()).getCourses().add(coursesMap.get(r.getCourseId()));
            }
        });
    }


}
