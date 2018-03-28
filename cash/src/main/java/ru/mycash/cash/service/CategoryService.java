package ru.mycash.cash.service;

import ru.mycash.cash.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category, Integer userId);
    Category update(Category category, Integer userId);
    void delete(Integer id, Integer userId);
    Category get(Integer id, Integer userId);
    List<Category> getAll(Integer userId);
}
