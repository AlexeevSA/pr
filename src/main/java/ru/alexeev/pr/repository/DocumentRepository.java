package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.Documents;

public interface DocumentRepository extends CrudRepository<Documents, Long> {
    Documents findBySNILS(String snils);
}
