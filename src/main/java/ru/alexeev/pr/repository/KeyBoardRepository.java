package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.Keyboard;

import java.util.List;

public interface KeyBoardRepository extends CrudRepository<Keyboard, Long> {

    List<Keyboard> findByFormPer(Integer formPer);

    List<Keyboard> findByNameContaining(String name);

}
