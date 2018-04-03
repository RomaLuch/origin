package ru.mycash.cash.model;

import java.time.LocalDateTime;


public class Record extends AbstractBaseEntity {
    private Category category;
    private LocalDateTime dateTime;
    private String description;
    private Integer amount;

    public Record(LocalDateTime dateTime, String description, Category category, Integer amount) {
        this(null,dateTime,description,category,amount);
    }

    public Record(Integer id, LocalDateTime dateTime, String description, Integer amount) {
        this(null,dateTime,description,null, amount);
    }

    public Record(Integer id, LocalDateTime dateTime, String description, Category category, Integer amount) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.category = category;
        this.amount = amount;
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

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", dateTimetime=" + dateTime +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
