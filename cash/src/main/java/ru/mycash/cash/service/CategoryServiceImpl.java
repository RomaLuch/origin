package ru.mycash.cash.service;


import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;
import ru.mycash.cash.repository.CategoryRepositoryImpl;

import java.util.ArrayList;
import java.util.List;



public class CategoryServiceImpl implements CategoryService {

   // private static final Logger log = getLogger(CategoryServiceImpl.class);

    CategoryRepository repoitory = new CategoryRepositoryImpl();

/*    public static void main(String[] args) {
        CategoryRepository repo = new CategoryRepositoryImpl();
        repo.getAll().stream().forEach(System.out::println);
    }*/

    @Override
    public void save(Category category) {
        repoitory.save(category);
    }

    @Override
    public void delete(Integer id) {
repoitory.delete(id);
    }

    @Override
    public Category get(Integer id) {
        return repoitory.get(id);
    }

    @Override
    public List<Category> getAll() {
     //   log.info("getAll");

        System.out.println("service getall");

        repoitory.getAll().stream().forEach(System.out::println);

        return new ArrayList<>(repoitory.getAll());
    }
}
