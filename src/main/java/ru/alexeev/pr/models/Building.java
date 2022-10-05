package ru.alexeev.pr.models;

import javax.persistence.*;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Adress;
    private String Type;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Floor floor;

    public Building() {
    }

    public Building(String adress, String type, Floor floor) {
        Adress = adress;
        Type = type;
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
