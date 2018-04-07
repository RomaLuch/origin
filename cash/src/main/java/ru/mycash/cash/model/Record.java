package ru.mycash.cash.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Record.DELETE, query = "DELETE FROM Record r WHERE r.id=:id AND r.user.id=:userId"),
        @NamedQuery(name = Record.GET, query = "SELECT r FROM Record r LEFT JOIN FETCH r.user WHERE r.id=:id AND r.user.id=:userId"),
        @NamedQuery(name = Record.ALL_SORTED, query = "SELECT r FROM Record r LEFT JOIN FETCH r.user WHERE r.user.id=:userId ORDER BY r.dateTime DESC "),
})

@Entity
@Table(name = "records", uniqueConstraints = {@UniqueConstraint(columnNames = {"datetime" , "id"}, name = "record_unique_date_idx")})
public class Record extends AbstractBaseEntity {

    public static final String DELETE = "Record.delete";
    public static final String GET= "Record.get";
    public static final String GET_BETWEEN = "Record.getBetween";
    public static final String ALL_SORTED = "Record.getAllSorted";


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "datetime", columnDefinition = "timestamp default now()", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Record(){}

    public Record(LocalDateTime dateTime, String description, Category category, Integer amount) {
        this(null,dateTime,description,category,amount);
    }

    public Record(Integer id, LocalDateTime dateTime, String description, Integer amount) {
        this(id,dateTime,description,null, amount);
    }

    public Record(Integer id, LocalDateTime dateTime, String description, Category category, Integer amount) {
        this(id,dateTime,description,category, amount,null);
    }

    public Record(Integer id, LocalDateTime dateTime, String description, Category category, Integer amount, User user
    ) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "category=" + category +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}
