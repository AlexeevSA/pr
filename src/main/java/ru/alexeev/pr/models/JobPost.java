package ru.alexeev.pr.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String partpost;

    @ManyToMany
    @JoinTable (name="worker_jobpost",
            joinColumns=@JoinColumn (name="jobpost_id"),
            inverseJoinColumns=@JoinColumn(name="worker_id"))
    private List<Worker> worker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartpost() {
        return partpost;
    }

    public void setPartpost(String partpost) {
        this.partpost = partpost;
    }

    public List<Worker> getWorker() {
        return worker;
    }

    public void setWorker(List<Worker> worker) {
        this.worker = worker;
    }
}
