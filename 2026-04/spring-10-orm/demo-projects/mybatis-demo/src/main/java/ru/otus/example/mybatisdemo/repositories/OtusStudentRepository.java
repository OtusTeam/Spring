package ru.otus.example.mybatisdemo.repositories;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.otus.example.mybatisdemo.models.Avatar;
import ru.otus.example.mybatisdemo.models.OtusStudent;

import java.util.List;

@Mapper
public interface OtusStudentRepository {

    @Select("select * from otus_students")
    @Results(id = "studentAllMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "avatar", column = "avatar_id", javaType = Avatar.class,
                    one = @One(select = "ru.otus.example.mybatisdemo.repositories.AvatarRepository.getAvatarById", fetchType = FetchType.EAGER)),
            @Result(property = "emails", column = "id", javaType = List.class,
                    many = @Many(select = "ru.otus.example.mybatisdemo.repositories.EmailRepository.getEmailsByStudentId", fetchType = FetchType.EAGER)),
            @Result(property = "courses", column = "id", javaType = List.class,
                    many = @Many(select = "ru.otus.example.mybatisdemo.repositories.CourseRepository.getCoursesByStudentId", fetchType = FetchType.EAGER))
    })
    List<OtusStudent> findAllWithAllInfo();

    @Select("select * from otus_students where id = #{id}")
    @ResultMap("studentAllMap")
    OtusStudent findById(long id);

    @Select("select count(*) as students_count from otus_students")
    long getStudentsCount();

    @Insert("insert into otus_students(name, avatar_id) values (#{name}, #{avatar.id})")
    void insert(OtusStudent student);

    @Update("update otus_students set name = #{name} where id = #{id}")
    void updateName(OtusStudent student);

    @Delete("delete from otus_students where id = #{id}")
    void deleteById(long id);

}
