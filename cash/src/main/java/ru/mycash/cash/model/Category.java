package ru.mycash.cash.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Category extends AbstractNamedEntity {

    public Category(Integer id, String name) {
        super(id, name);
    }

    public Category(String name) {
        this(null,name);
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
