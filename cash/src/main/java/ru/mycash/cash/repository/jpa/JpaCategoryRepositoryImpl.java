package ru.mycash.cash.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.User;
import ru.mycash.cash.repository.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaCategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Category save(Category category, Integer userId) {
        if (category.isNew()) {
            User user = em.find(User.class, userId);
            category.setUser(user);
            em.persist(category);
            return category;
        } else {
            System.out.println(category);
            System.out.println(category.getUser());
            return (category.getUser().getId()!=userId)?null: em.merge(category);
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id, Integer userId) {
        return em.createNamedQuery(Category.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Category get(Integer id, Integer userId) {
        List<Category> result = em.createNamedQuery(Category.GET)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return result.isEmpty()?null:result.get(0);
    }

    @Override
    public Collection<Category> getAll(Integer userId) {
        return em.createNamedQuery(Category.ALL_SORTED, Category.class)
                .setParameter("userId", userId).getResultList();
    }
}
