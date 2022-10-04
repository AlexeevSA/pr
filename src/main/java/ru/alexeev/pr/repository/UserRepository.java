package ru.alexeev.pr.repository;


import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
