package ru.alexeev.pr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Post {
    public Post(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
    }

    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(message = "Длинна строки от 2 до 150 символов", min = 2, max = 150)
    private String title;
    @NotBlank(message = "Поле не должно быть пустым")
    private String anons;
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String full_text;
    @Max(value = 15000, message = "Не может быть больше 15000")
    @Min(value = 0, message = "Число не должно быть отрицательным")
    @NotNull(message = "Впишите число")
    private int views;

//    @Null
//    @Email
//    @DecimalMax()
//    @DecimalMin()
//    @Digits()
//    @Negative
//    @NegativeOrZero
//    @Positive
//    @PositiveOrZero
//    @Future
//    @FutureOrPresent
//    @Past
//    @PastOrPresent
//    @AssertFalse
//    @AssertTrue



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
