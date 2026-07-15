package ru.otus.example.springjdbcdemo.repositories.ext;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.example.springjdbcdemo.models.Avatar;
import ru.otus.example.springjdbcdemo.models.EMail;
import ru.otus.example.springjdbcdemo.models.OtusStudent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OtusStudentResultSetExtractor implements
        ResultSetExtractor<Map<Long, OtusStudent>> {
    @Override
    public Map<Long, OtusStudent> extractData(ResultSet rs) throws SQLException,
            DataAccessException {

        Map<Long, OtusStudent> students = new HashMap<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            OtusStudent student = students.get(id);
            if (student == null) {
                student = new OtusStudent(id, rs.getString("name"),
                        new Avatar(rs.getLong("avatar_id"), rs.getString("photo_url")),
                        new ArrayList<>(), new ArrayList<>());
                students.put(student.getId(), student);
            }

            student.getEmails().add(new EMail(rs.getLong("email_id"),
                    rs.getString("email")));
        }
        return students;
    }
}
