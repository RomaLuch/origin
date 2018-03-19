package ru.mycash.cash.service;

import org.slf4j.Logger;
import ru.mycash.cash.repository.Repository;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.mock.InMemoryRecordRepositoryImpl;
import ru.mycash.cash.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithId;

public class ServiseImpl implements Service{

    private static final Logger log = getLogger(ServiseImpl.class);

    Repository repository = new InMemoryRecordRepositoryImpl();

    @Override
    public Record create(Record record, Integer userId) {
        log.info("update category id({})", record);
        return repository.save(record, userId);
    }

    @Override
    public void update(Record record, Integer userId) {
        log.info("update category id({})", record);
        repository.save(record, userId);
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.info("delet category id({})", id);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Record get(Integer id, Integer userId) {
        log.info("get category id({})", id);
        return repository.get(id, userId);
    }

    @Override
    public List<Record> getAll(Integer userId) {
        log.info("getAll");
        return new ArrayList<>(repository.getAll(userId));
    }
}
