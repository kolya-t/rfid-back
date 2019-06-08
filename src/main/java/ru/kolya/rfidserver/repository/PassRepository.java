package ru.kolya.rfidserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kolya.rfidserver.model.Pass;
import ru.kolya.rfidserver.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PassRepository extends CrudRepository<Pass, Long> {
    List<Pass> findAll();

    List<Pass> findAllByUser(@Param("user") User user);

    @Query("select p from Pass p where user = :user order by id desc")
    Pass findLastByUser(@Param("user") User user);

    @Query("select p from Pass p " +
            "where p.user = :user " +
            "and p.come >= :date1 " +
            "and p.come <= :date2 " +
            "and p.gone >= :date1 " +
            "and p.gone <= :date2")
    List<Pass> findAllByUserAndBetweenDates(@Param("user") User user,
                                            @Param("date1") LocalDateTime instant1,
                                            @Param("date2") LocalDateTime instant2);
}
