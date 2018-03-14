package ru.mycash.cash.repository;

import org.slf4j.Logger;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class RepositoryImpl implements Repository{

    private static final Logger log = getLogger(RepositoryImpl.class);

    private static AtomicInteger count = new AtomicInteger(0);

    private Map<Integer, Record> repository = new ConcurrentHashMap<>();


    public static final List<Record> RECORDS = Arrays.asList(
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "обед",new Category("еда"), 500),
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 11, 0), "такси", new Category("транспорт"), 100),
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 12, 0), "кино", new Category("развлечение"), 600)
    );


    {
        RECORDS.forEach(this::save);
    }

    @Override
    public void save(Record record) {
        log.info("save {}",record);
    if(record.isNew())
    {
    record.setId(count.incrementAndGet());
    repository.put(record.getId(),record);
    }
    else repository.computeIfPresent(record.getId(),(id, oldValue)->record);
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