package ru.mycash.cash.repository;

import ru.mycash.cash.model.Category;

import java.util.Collection;

public interface CategoryRepository {
    void save(Category category);
    void delete(Integer id);
    Category get(Integer id);
    Collection<Category> getAll();
}
