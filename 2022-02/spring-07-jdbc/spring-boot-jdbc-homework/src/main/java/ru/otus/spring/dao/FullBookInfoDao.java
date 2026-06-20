package ru.otus.spring.dao;

import ru.otus.spring.domain.FullBookInfo;

import java.util.List;

public interface FullBookInfoDao {

    List<FullBookInfo> getAll();

    FullBookInfo getById(long id);

    FullBookInfo getByName(String name);
}
