package ru.mycash.cash.service;

import ru.mycash.cash.model.Record;

import java.util.Collection;
import java.util.List;

public interface Service {
    void update(Record record);
    Record create (Record record);
    void delete(Integer id);
    Record get(Integer id);
    List<Record> getAll();
}
