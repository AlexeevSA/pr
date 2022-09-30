package ru.alexeev.pr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Keyboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String name;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String type;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String switches;
    @Positive(message = "Не должно быть отрицательным")
    @NotNull(message = "Поле не должно быть пустым")
    @Max(value = 100)
    private Integer formPer;
    @Positive(message = "Не должно быть отрицательным")
    @NotNull(message = "Поле не должно быть пустым")
    @Max(value = 120)
    private Integer keyNums;

    public Keyboard( String name, String type, String switches, Integer formPer, Integer keyNums) {
        this.name = name;
        this.type = type;
        this.switches = switches;
        this.formPer = formPer;
        this.keyNums = keyNums;
    }

    public Keyboard() {
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public Integer getFormPer() {
        return formPer;
    }

    public void setFormPer(Integer formPer) {
        this.formPer = formPer;
    }

    public Integer getKeyNums() {
        return keyNums;
    }

    public void setKeyNums(Integer keyNums) {
        this.keyNums = keyNums;
    }
}
