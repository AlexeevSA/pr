package ru.alexeev.pr.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String floors;
    private String info;

    @OneToMany(mappedBy = "floor", fetch = FetchType.EAGER)
    private Collection<Building> home;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Collection<Building> getHome() {
        return home;
    }

    public void setHome(Collection<Building> home) {
        this.home = home;
    }
}
