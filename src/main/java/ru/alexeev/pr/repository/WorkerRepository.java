package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    Worker findByFirstname(String firstname);
}
