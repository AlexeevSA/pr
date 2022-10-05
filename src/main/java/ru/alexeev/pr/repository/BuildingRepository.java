package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.Building;

public interface BuildingRepository extends CrudRepository<Building, Long> {

}
