package ru.mycash.cash.repository;

import ru.mycash.cash.model.Category;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class CategoryRepositoryImpl implements CategoryRepository {

  //  private static final Logger log = getLogger(CategoryRepositoryImpl.class);

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
        System.out.println("save");
        if(category.isNew())
        {
            System.out.println("save new" + category);
            category.setId(count.incrementAndGet());
            repository.put(category.getId(),category);
            System.out.println("after save new" + category);
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
  //      log.info("getAll");
        System.out.println("Category repository getall");
        Collection<Category> result = repository.values();
        System.out.println(result);
        return result/*.
                stream()
                .sorted(Comparator.comparing(Category::getName))
                .collect(Collectors.toList())*/;
    }
}
