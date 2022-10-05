package ru.alexeev.pr.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexeev.pr.models.JobPost;

public interface JobPostRepository extends CrudRepository<JobPost, Long> {
    JobPost findByName(String name);
}
