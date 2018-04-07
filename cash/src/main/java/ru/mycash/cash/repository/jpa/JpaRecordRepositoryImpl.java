package ru.mycash.cash.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.model.User;
import ru.mycash.cash.repository.RecordRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRecordRepositoryImpl implements RecordRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Record save(Record record, Integer userId) {
        if (record.isNew()) {
            User user = em.find(User.class, userId);
            record.setUser(user);
            em.persist(record);
            return record;
        } else {
            return (record.getUser().getId()!=userId)?null: em.merge(record);
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id, Integer userId) {
        return em.createNamedQuery(Record.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Record get(Integer id, Integer userId) {
        List<Record> result = em.createNamedQuery(Record.GET)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return result.isEmpty()?null:result.get(0);
    }

    @Override
    public Collection<Record> getAll(Integer userId) {
        return em.createNamedQuery(Record.ALL_SORTED, Record.class)
                .setParameter("userId", userId).getResultList();
    }
}
