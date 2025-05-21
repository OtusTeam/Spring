package ru.otus.spring.service;

import ru.otus.spring.model.NoticeMessage;

import java.util.List;

public interface NoticeService {

    NoticeMessage create(NoticeMessage message);

    NoticeMessage get(Integer id);

    List<NoticeMessage> getAll();

    NoticeMessage update(NoticeMessage message);
}
