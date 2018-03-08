package ru.mycash.cash.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Record {
    private Integer id;
    private LocalDateTime dateTimetime;
    private String description;
    private Integer amount;

    public Record(LocalDateTime dateTimetime, String description, Integer value) {
        this.dateTimetime = dateTimetime;
        this.description = description;
        this.amount = value;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTimetime;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setDateTimetime(LocalDateTime dateTimetime) {
        this.dateTimetime = dateTimetime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew()
    {
        return id==null;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", dateTimetime=" + dateTimetime +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
