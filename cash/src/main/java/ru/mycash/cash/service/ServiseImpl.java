package ru.mycash.cash.service;

import org.slf4j.Logger;
import ru.mycash.cash.repository.Repository;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ServiseImpl implements Service{

    private static final Logger log = getLogger(ServiseImpl.class);

    Repository repository = new RepositoryImpl();

    @Override
    public Record create(Record record) {
        log.info("update category id({})", record);
        return repository.save(record);
    }

    @Override
    public void update(Record record) {
        log.info("update category id({})", record);
        repository.save(record);
    }

    @Override
    public void delete(Integer id) {
        log.info("delet category id({})", id);
        repository.delete(id);
    }

    @Override
    public Record get(Integer id) {
        log.info("get category id({})", id);
        return repository.get(id);
    }

    @Override
    public List<Record> getAll() {
        log.info("getAll");
        return new ArrayList<>(repository.getAll());
    }
}
