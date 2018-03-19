package ru.mycash.cash.service;

import ru.mycash.cash.model.Record;

import java.util.Collection;
import java.util.List;

public interface Service {
    Record update(Record record, Integer userId);
    Record create (Record record, Integer userId);
    void delete(Integer id, Integer userId);
    Record get(Integer id, Integer userId);
    List<Record> getAll(Integer userId);
}
