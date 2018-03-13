package ru.mycash.cash.repository;

import ru.mycash.cash.model.Category;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, Category> repository = new ConcurrentHashMap<>();

public static final List<Category> CATEGORY = Arrays.asList(new Category("еда"),
                                                        new Category("транспорт"),
                                                        new Category("развлечения"));

    {
        CATEGORY.forEach(this::save);
    }

    @Override
    public void save(Category category) {
        if(category.isNew())
        {
            category.setId(count.incrementAndGet());
            repository.put(category.getId(),category);
        }
        else repository.computeIfPresent(category.getId(),(id, oldValue)->category);
    }

    @Override
    public void delete(Integer id) {
repository.remove(id);
    }

    @Override
    public Category get(Integer id) {
        return repository.get(id);
    }

    @Override
    public Collection<Category> getAll() {
        return repository.values().
                stream()
                .sorted(Comparator.comparing(Category::getName))
                .collect(Collectors.toList());
    }
}
