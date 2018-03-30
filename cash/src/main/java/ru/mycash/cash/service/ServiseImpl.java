package ru.mycash.cash.service;

import org.slf4j.Logger;
import ru.mycash.cash.repository.Repository;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.mock.InMemoryRecordRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.mycash.cash.util.TimeUtil.*;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithId;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithIdBoolean;

public class ServiseImpl implements Service{

    private static final Logger log = getLogger(ServiseImpl.class);

    Repository repository;

    public ServiseImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Record create(Record record, Integer userId) {
        log.info("update category id({})", record);
        return checkNotFoundWithId(repository.save(record, userId), record.getId());
    }

    @Override
    public Record update(Record record, Integer userId) {
        log.info("update category id({})", record);
        return checkNotFoundWithId(repository.save(record, userId),record.getId());
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.info("delet category id({})", id);
        checkNotFoundWithIdBoolean(repository.delete(id, userId), id);
    }

    @Override
    public Record get(Integer id, Integer userId) {
        log.info("get category id({})", id);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Record> getAll(Integer userId) {
        log.info("getAll");
        return new ArrayList<>(repository.getAll(userId));
    }

    @Override
    public List<Record> getAllFiltred(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer categoryId, Integer userId) {

        log.info("getAllFiltred");

        return categoryId==-1?repository.getAll(userId)
                .stream()
                .filter(record -> isBetween(record.getDateTime().toLocalDate(),startDate,endDate))
                .filter(record -> isBetween(record.getDateTime().toLocalTime(),startTime,endTime))
                .collect(Collectors.toList())
        :repository.getAll(userId)
                .stream()
                .filter(record -> isBetween(record.getDateTime().toLocalDate(),startDate,endDate))
                .filter(record -> isBetween(record.getDateTime().toLocalTime(),startTime,endTime))
                .filter(record -> record.getCategory().getId()==categoryId)
                .collect(Collectors.toList());
    }
}
