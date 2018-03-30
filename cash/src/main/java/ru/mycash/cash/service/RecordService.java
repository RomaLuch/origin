package ru.mycash.cash.service;

import ru.mycash.cash.model.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordService {
    Record update(Record record, Integer userId);
    Record create (Record record, Integer userId);
    void delete(Integer id, Integer userId);
    Record get(Integer id, Integer userId);
    List<Record> getAll(Integer userId);
    List<Record> getAllFiltred(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer categoryId, Integer userId);
}
