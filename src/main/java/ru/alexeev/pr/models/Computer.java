package ru.alexeev.pr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String cpu;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String gpu;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Текст должен быть больше 5 символов")
    private String chipset;
    @Max(value = 64000, message = "Не может быть больше 64000")
    @Min(value = 0, message = "Число не должно быть отрицательным")
    @NotNull(message = "Поле не должно быть пустым")
    private Integer ramMb;
    @Positive(message = "Не должно быть отрицательным")
    @NotNull(message = "Поле не должно быть пустым")
    private Integer romGb;

    public Computer( String cpu, String gpu, String chipset, Integer ramMb, Integer romGb) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.chipset = chipset;
        this.ramMb = ramMb;
        this.romGb = romGb;
    }

    public Computer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getRamMb() {
        return ramMb;
    }

    public void setRamMb(Integer ramMb) {
        this.ramMb = ramMb;
    }

    public Integer getRomGb() {
        return romGb;
    }

    public void setRomGb(Integer romGb) {
        this.romGb = romGb;
    }
}
