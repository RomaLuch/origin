package ru.mycash.cash.model;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NamedQueries({
        @NamedQuery(name = Category.DELETE, query = "DELETE FROM Category c WHERE c.id=:id AND c.user.id=:userId"),
        @NamedQuery(name = Category.GET, query = "SELECT c FROM Category c LEFT JOIN FETCH c.user WHERE c.id=:id AND c.user.id=:userId"),
        @NamedQuery(name = Category.ALL_SORTED, query = "SELECT c FROM Category c LEFT JOIN FETCH c.user WHERE c.user.id=:userId ORDER BY c.name"),
})

@Entity
@Table(name = "categories")
public class Category extends AbstractNamedEntity {

    public static final String DELETE = "Category.delete";
    public static final String GET= "Category.get";
    public static final String GET_BETWEEN = "Category.getBetween";
    public static final String ALL_SORTED = "Category.getAllSorted";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Category(){}

    public Category(Integer id, String name) {
        super(id, name);
    }

    public Category(String name) {
        this(null,name);
    }

    public Category(String name, User user)
    {
        this(name);
        this.user = user;
    }

    public Category(Integer id,String name, User user)
    {
        super(id,name);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNew()
    {
        return id==null;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
