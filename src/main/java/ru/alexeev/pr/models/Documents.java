package ru.alexeev.pr.models;


import javax.persistence.*;

@Entity
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String SNILS;
    private String INN;

    @OneToOne(optional = true, mappedBy = "documents")
    private Person owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSNILS() {
        return SNILS;
    }

    public void setSNILS(String SNILS) {
        this.SNILS = SNILS;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
