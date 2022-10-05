package ru.alexeev.pr.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String LastName;

    @ManyToMany
    @JoinTable (name="worker_jobpost",
            joinColumns=@JoinColumn (name="worker_id"),
            inverseJoinColumns=@JoinColumn(name="jobpost_id"))
    private List<JobPost> jobpost;

    public Worker(Long id, String firstName, String lastName, List<JobPost> jobpost) {
        this.id = id;
        firstname = firstName;
        LastName = lastName;
        this.jobpost = jobpost;
    }

    public Worker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        firstname = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public List<JobPost> getJobpost() {
        return jobpost;
    }

    public void setJobpost(List<JobPost> jobpost) {
        this.jobpost = jobpost;
    }
}
