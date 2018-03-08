package ru.mycash.cash.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Record {
    private static AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger id;
    private LocalDateTime dateTimetime;
    private String description;
    private Integer amount;

    public Record(LocalDateTime dateTimetime, String description, Integer value) {
        this.id =  new AtomicInteger(count.incrementAndGet());
        this.dateTimetime = dateTimetime;
        this.description = description;
        this.amount = value;
    }

    public AtomicInteger getId() {
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

    public void setId(AtomicInteger id) {
        this.id = id;
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
