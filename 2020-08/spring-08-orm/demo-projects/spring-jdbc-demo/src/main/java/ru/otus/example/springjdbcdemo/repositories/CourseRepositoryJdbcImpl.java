package ru.otus.example.springjdbcdemo.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.example.springjdbcdemo.models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryJdbcImpl implements CourseRepositoryJdbc {

    @Autowired
    private final JdbcOperations op;

    @Override
    public List<Course> findAllUsed() {
        return op.query("select c.id, c.name " +
                          "from courses c inner join student_courses sc on c.id = sc.course_id " +
                          "group by c.id, c.name " +
                          "order by c.name", new CourseRowMapper());
    }

    private class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int i) throws SQLException {
            return new Course(rs.getLong(1), rs.getString(2));
        }
    }

}
