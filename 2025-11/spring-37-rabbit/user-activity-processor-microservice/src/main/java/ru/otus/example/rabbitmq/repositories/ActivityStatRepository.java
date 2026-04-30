package ru.otus.example.rabbitmq.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.useractivitymodels.ActivityStatElem;

import java.util.List;

@Transactional
public interface ActivityStatRepository extends JpaRepository<ActivityStatElem, Long> {

    @Transactional(readOnly = true)
    @Query("select new ru.otus.example.useractivitymodels.ActivityStatElem(u.name, t.name, count(a)) " +
            "from UserActivity a left join a.appUser u left join a.type t " +
            "group by u.name, t.name " +
            "order by count(a) desc, u.name, t.name")
    List<ActivityStatElem> calcActivityStat();

}
