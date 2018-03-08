package ru.mycash.cash.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Record {
    private Integer id;
    private LocalDateTime dateTime;
    private String description;
    private Integer amount;

    public Record(LocalDateTime dateTime, String description, Integer value) {
        this(null,dateTime,description,value);
    }

    public Record(Integer id, LocalDateTime dateTime, String description, Integer amount) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
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
                ", dateTimetime=" + dateTime +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
