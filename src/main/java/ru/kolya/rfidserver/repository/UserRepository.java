package ru.kolya.rfidserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kolya.rfidserver.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByCardNumber(@Param("cardNumber") String number);
}
