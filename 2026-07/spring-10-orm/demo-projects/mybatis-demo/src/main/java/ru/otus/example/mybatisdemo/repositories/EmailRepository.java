package ru.otus.example.mybatisdemo.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.otus.example.mybatisdemo.models.EMail;

import java.util.List;

@Mapper
public interface EmailRepository {

    @Select("select * from emails where student_id = #{studentId}")
    List<EMail> getEmailsByStudentId(long studentId);
}
