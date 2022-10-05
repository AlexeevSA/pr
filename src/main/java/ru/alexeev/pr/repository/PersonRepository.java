package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
