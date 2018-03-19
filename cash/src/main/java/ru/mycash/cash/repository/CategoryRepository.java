package ru.mycash.cash.repository;

import ru.mycash.cash.model.Category;

import java.util.Collection;

public interface CategoryRepository {
    Category save(Category category, Integer userId);
    boolean delete(Integer id, Integer userId);
    Category get(Integer id, Integer userId);
    Collection<Category> getAll(Integer userId);
}
