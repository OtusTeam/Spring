package ru.otus.smigunov.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.smigunov.project.domain.CheckList;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
   List<CheckList> findByUserid(Long userid);
}