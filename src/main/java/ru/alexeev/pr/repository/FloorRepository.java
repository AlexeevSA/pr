package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.Floor;

public interface FloorRepository extends CrudRepository<Floor, Long> {
    Floor findByInfo(String info);
}
