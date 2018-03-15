package ru.mycash.cash.repository.mock;

import org.slf4j.Logger;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryRepositoryImpl implements Repository {

    private static final Logger log = getLogger(InMemoryRepositoryImpl.class);

    private static AtomicInteger count = new AtomicInteger(0);

    private Map<Integer, Record> repository = new ConcurrentHashMap<>();


    public static final List<Record> RECORDS = Arrays.asList(
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "обед",new Category(1,"еда"), 500),
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 11, 0), "такси", new Category(2,"транспорт"), 100),
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 12, 0), "кино", new Category(3,"развлечения"), 600)
    );


    {
        RECORDS.forEach(this::save);
    }

    @Override
    public Record save(Record record) {
        log.info("save {}",record);
    if(record.isNew())
    {
    record.setId(count.incrementAndGet());
    return repository.put(record.getId(),record);
    }
    else return repository.computeIfPresent(record.getId(),(id, oldValue)->record);
    }

    @Override
    public void delete(Integer id) {

        log.info("delete record id({})", id);
        repository.remove(id);
    }

    @Override
    public Record get(Integer id) {
        log.info("get record id({})", id);
        return repository.get(id);
    }

    @Override
    public Collection<Record> getAll() {
        log.info("getAll");
        return repository.values();
    }
}