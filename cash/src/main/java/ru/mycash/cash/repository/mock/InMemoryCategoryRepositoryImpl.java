package ru.mycash.cash.repository.mock;

import org.slf4j.Logger;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;


public class InMemoryCategoryRepositoryImpl implements CategoryRepository {

private static final Logger log = getLogger(InMemoryCategoryRepositoryImpl.class);
    private static AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, Map<Integer, Category>> repository = new ConcurrentHashMap<>();

public static final List<Category> CATEGORY = Arrays.asList(new Category("еда"),
                                                        new Category("транспорт"),
                                                        new Category("развлечения"));



    {
        CATEGORY.forEach(category -> save(category, 1));
    }

    @Override
    public Category save(Category category, Integer userId) {
        log.info("Save {}", category);
        Map<Integer,Category> categories = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if(category.isNew()){
            category.setId(count.incrementAndGet());
            categories.put(category.getId(),category);
            return category;
        }
            return categories.computeIfPresent(category.getId(), (id, oldValue) -> category);
        }

    @Override
    public boolean delete(Integer id, Integer userId) {
        log.info("delet category id({})", id);
        Map<Integer,Category> categories = repository.get(userId);
        return categories!=null&&categories.get(id)!=null;
    }

    @Override
    public Category get(Integer id, Integer userId) {
        log.info("get id({})", id);
        Map<Integer,Category> categories = repository.get(userId);
        return categories==null?null:categories.get(id);
    }

    @Override
    public Collection<Category> getAll(Integer userId) {
        log.info("getAll");
        Map<Integer,Category> categories = repository.get(userId);
        return categories==null? Collections.emptyList()
                :categories.values()
                .stream()
                .sorted(Comparator.comparing(Category::getName))
                .collect(Collectors.toList());
    }
}
