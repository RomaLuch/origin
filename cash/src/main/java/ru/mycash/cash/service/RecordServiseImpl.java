package ru.mycash.cash.service;

//import org.springframework.stereotype.RecordService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycash.cash.repository.RecordRepository;
import ru.mycash.cash.model.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.mycash.cash.util.TimeUtil.*;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithId;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithIdBoolean;

@Service
public class RecordServiseImpl implements RecordService {

    private static final Logger log = getLogger(RecordServiseImpl.class);

    @Autowired
    private RecordRepository recordRepository;

    public RecordServiseImpl() {
    }

    @Override
    public Record create(Record record, Integer userId) {
        log.info("update category id({})", record);
        return checkNotFoundWithId(recordRepository.save(record, userId), record.getId());
    }

    @Override
    public Record update(Record record, Integer userId) {
        log.info("update category id({})", record);
        return checkNotFoundWithId(recordRepository.save(record, userId),record.getId());
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.info("delet category id({})", id);
        checkNotFoundWithIdBoolean(recordRepository.delete(id, userId), id);
    }

    @Override
    public Record get(Integer id, Integer userId) {
        log.info("get category id({})", id);
        return checkNotFoundWithId(recordRepository.get(id, userId), id);
    }

    @Override
    public List<Record> getAll(Integer userId) {
        log.info("getAll");
        return new ArrayList<>(recordRepository.getAll(userId));
    }

    @Override
    public List<Record> getAllFiltred(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer categoryId, Integer userId) {

        log.info("getAllFiltred");

        return categoryId==-1? recordRepository.getAll(userId)
                .stream()
                .filter(record -> isBetween(record.getDateTime().toLocalDate(),startDate,endDate))
                .filter(record -> isBetween(record.getDateTime().toLocalTime(),startTime,endTime))
                .collect(Collectors.toList())
        : recordRepository.getAll(userId)
                .stream()
                .filter(record -> isBetween(record.getDateTime().toLocalDate(),startDate,endDate))
                .filter(record -> isBetween(record.getDateTime().toLocalTime(),startTime,endTime))
                .filter(record -> record.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }
}
