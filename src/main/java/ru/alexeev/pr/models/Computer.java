package ru.alexeev.pr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpu, gpu, chipset;
    private Integer ramMb, romGb;

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
