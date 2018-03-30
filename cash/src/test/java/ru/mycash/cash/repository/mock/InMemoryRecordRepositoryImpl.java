package ru.mycash.cash.repository.mock;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.mycash.cash.RecordTestData;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.RecordRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.mycash.cash.RecordTestData.*;


@Repository
public class InMemoryRecordRepositoryImpl implements RecordRepository {

    private static final Logger log = getLogger(InMemoryRecordRepositoryImpl.class);

    private static AtomicInteger count = new AtomicInteger(0);

    private Map<Integer, Map<Integer, Record>> repository = new ConcurrentHashMap<>();


    public InMemoryRecordRepositoryImpl() {
    }

    public void initToTests()
    {
        System.out.println("------INIT------");
        repository.clear();
        Map<Integer, Record> adminRecords = new ConcurrentHashMap<Integer, Record>();
        Map<Integer, Record> userRecords = new ConcurrentHashMap<Integer, Record>();
        adminRecords.put(RECORD_ID_1,RecordTestData.RECORD_1);
        adminRecords.put(RECORD_ID_2,RecordTestData.RECORD_2);
        userRecords.put(RECORD_ID_3,RecordTestData.RECORD_3);
        repository.put(ADMIN_ID, adminRecords);
        repository.put(USER_ID,userRecords);
        repository.keySet().stream().forEach(System.out::println);
        repository.values().stream().forEach(System.out::println);
    }

    public static final List<Record> RECORDS = Arrays.asList(
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "обед",new Category(1,"еда"), 500),
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 11, 0), "такси", new Category(2,"транспорт"), 100),
        new Record(LocalDateTime.of(2018, Month.MAY, 30, 12, 0), "кино", new Category(3,"развлечения"), 600)
    );


    {
        RECORDS.forEach(record -> save(record, 1));
    }

    @Override
    public Record save(Record record, Integer userId) {
        log.info("save {}", record);
        Map<Integer, Record> records = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (record.isNew()) {
            record.setId(count.incrementAndGet());
            records.put(record.getId(), record);
            return record;
        }
        return records.computeIfPresent(record.getId(), (id, oldValue) -> record);
    }

    @Override
    public boolean delete(Integer id, Integer userId) {

        log.info("delete record id({})", id);
        Map<Integer, Record> records = repository.get(userId);
        return records!=null && records.remove(id)!=null;
    }

    @Override
    public Record get(Integer id, Integer userId) {
        log.info("get record id({})", id);
        Map<Integer, Record> records = repository.get(userId);
        return records==null? null: records.get(id);
    }

    @Override
    public Collection<Record> getAll(Integer userId) {
        log.info("getAll");
        Map<Integer, Record> records = repository.get(userId);
        return records==null ? Collections.emptyList()
                :records.values()
                .stream()
                .sorted(Comparator.comparing(Record::getDateTime).reversed())
                .collect(Collectors.toList());

    }
}