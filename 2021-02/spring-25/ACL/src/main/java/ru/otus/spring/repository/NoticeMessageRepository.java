package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.model.NoticeMessage;

import java.util.List;
import java.util.Optional;

public interface NoticeMessageRepository extends JpaRepository<NoticeMessage, Integer> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<NoticeMessage> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    NoticeMessage getById(Integer id);

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasPermission(#noticeMessage, 'WRITE')")
    NoticeMessage save(@Param("noticeMessage") NoticeMessage noticeMessage);

}
